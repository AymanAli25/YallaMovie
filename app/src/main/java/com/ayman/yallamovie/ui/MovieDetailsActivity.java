package com.ayman.yallamovie.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ayman.yallamovie.api.callback.OnGetCastCallback;
import com.ayman.yallamovie.api.callback.OnGetMoviesCallback;
import com.ayman.yallamovie.api.data.Cast;
import com.ayman.yallamovie.repository.MoviesRepository;
import com.ayman.yallamovie.R;
import com.ayman.yallamovie.api.callback.OnGetGenresCallback;
import com.ayman.yallamovie.api.callback.OnGetMovieCallback;
import com.ayman.yallamovie.api.data.Genre;
import com.ayman.yallamovie.api.data.Movie;
import com.ayman.yallamovie.asynctask.AddFavouriteTask;
import com.ayman.yallamovie.database.DatabaseClient;
import com.ayman.yallamovie.database.MovieEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailsActivity";

    public static String MOVIE_ID = "movie_id";

    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";
    private static String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%s";
    private static String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%s/0.jpg";
    private static String IMDB_BASE_URL = "https://www.imdb.com/title/";

    private Button addToFavs;
    private ImageView movieBackdrop;
    private TextView movieTitle;
    private TextView movieGenres;
    private TextView movieOverview;
    private TextView movieOverviewLabel;
    private TextView movieReleaseDate;
    private RatingBar movieRating;
    private TextView movieImdbLink;
    private Button movieShare;
    private LinearLayout similarMovies;
    private TextView similarMoviesLable;
    private LinearLayout movieCast;
    private TextView castLabel;
    private boolean isFav;

    private MoviesRepository moviesRepository;
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieId = getIntent().getIntExtra(MOVIE_ID, movieId);

        checkFav(new Integer(movieId));

        moviesRepository = MoviesRepository.getInstance();

        initUI();

        getMovie();
    }

    private void initUI() {
        movieBackdrop = findViewById(R.id.movieDetailsBackdrop);
        movieTitle = findViewById(R.id.movieDetailsTitle);
        movieGenres = findViewById(R.id.movieDetailsGenres);
        movieOverview = findViewById(R.id.movieDetailsOverview);
        movieOverviewLabel = findViewById(R.id.summaryLabel);
        movieReleaseDate = findViewById(R.id.movieDetailsReleaseDate);
        movieRating = findViewById(R.id.movieDetailsRating);
        similarMovies = findViewById(R.id.similarMovies);
        movieCast = findViewById(R.id.movieCast);
        addToFavs = findViewById(R.id.click_me);
        movieImdbLink = findViewById(R.id.movieDetailsImdbLink);
        movieShare = findViewById(R.id.movieDetailsShare);
        similarMoviesLable = findViewById(R.id.similarMoviesLabel);
        castLabel = findViewById(R.id.castLabel);
    }

    private void getMovie() {
        moviesRepository.getMovieDetails(movieId, new OnGetMovieCallback() {
            @Override
            public void onSuccess(final Movie movie) {
                setupMovie(movie);
                getSimilarMovies(movie.getId());
                getReviews(movie.getId());
            }

            @Override
            public void onError() {
                finish();
            }
        });
    }

    private void setupMovie(final Movie movie) {

        movieTitle.setText(movie.getTitle());
        movieOverviewLabel.setVisibility(View.VISIBLE);
        movieOverview.setText(movie.getOverview());
        movieRating.setVisibility(View.VISIBLE);
        movieRating.setRating(movie.getRating() / 2);
        getGenres(movie);
        movieReleaseDate.setText(movie.getReleaseDate());
        movieImdbLink.setText(IMDB_BASE_URL + movie.getImdb_id());
        if (!isFinishing()) {
            Glide.with(MovieDetailsActivity.this)
                    .load(IMAGE_BASE_URL + movie.getBackdrop())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(movieBackdrop);
        }
        if(isFav)
            addToFavs.setVisibility(View.INVISIBLE);
        else
            addToFavs.setVisibility(View.VISIBLE);

        movieShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, IMDB_BASE_URL + movie.getImdb_id());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        addToFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieEntity me = new MovieEntity();
                me.setId(movie.getId());
                me.setTitle(movie.getTitle());
                me.setPosterPath(movie.getPosterPath());

                AddFavouriteTask addFavouriteTask = new AddFavouriteTask(getApplicationContext());
                addFavouriteTask.execute(me);

                addToFavs.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getSimilarMovies(int id) {
        moviesRepository.getSimilarMovies(id, new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                similarMoviesLable.setVisibility(View.VISIBLE);
                similarMovies.removeAllViews();
                for (final Movie movie : movies) {
                    View parent = getLayoutInflater().inflate(R.layout.item_similar_movie, similarMovies, false);

                    TextView title = parent.findViewById(R.id.item_similar_title);
                    title.setText(movie.getTitle());

                    ImageView thumbnail = parent.findViewById(R.id.item_similar_poster);
                    thumbnail.requestLayout();
                    Glide.with(MovieDetailsActivity.this)
                            .load(IMAGE_BASE_URL + movie.getBackdrop())
                            .apply(RequestOptions.placeholderOf(R.color.colorPrimary).centerCrop())
                            .into(thumbnail);
                    similarMovies.addView(parent);
                }
            }

            @Override
            public void onError() {
                // Do nothing
                similarMoviesLable.setVisibility(View.GONE);
            }
        });
    }

    private void getReviews(int id) {
        moviesRepository.getCast(id, new OnGetCastCallback() {
            @Override
            public void onSuccess(List<Cast> casts) {
                castLabel.setVisibility(View.VISIBLE);
                movieCast.removeAllViews();
                for (Cast cast : casts) {
                    View parent = getLayoutInflater().inflate(R.layout.item_cast, movieCast, false);
                    TextView character = parent.findViewById(R.id.item_cast_character);
                    TextView castName = parent.findViewById(R.id.item_cast_name);
                    character.setText(cast.getCharacter());
                    castName.setText(cast.getName());
                    movieCast.addView(parent);
                }
            }

            @Override
            public void onError() {
                castLabel.setVisibility(View.VISIBLE);
            }
        });
    }


    private void getGenres(final Movie movie) {
        moviesRepository.getGenres(new OnGetGenresCallback() {
            @Override
            public void onSuccess(List<Genre> genres) {
                if (movie.getGenres() != null) {
                    List<String> currentGenres = new ArrayList<>();
                    for (Genre genre : movie.getGenres()) {
                        currentGenres.add(genre.getName());
                    }
                    movieGenres.setText(TextUtils.join(", ", currentGenres));
                }
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showError() {
        Toast.makeText(MovieDetailsActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
    }


    private void checkFav(Integer id)
    {
        class IsFavourites extends AsyncTask<Integer, Void, MovieEntity> {

            @Override
            protected MovieEntity doInBackground(Integer... integers) {
                MovieEntity favourite = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .movieDAO()
                        .loadFavourite(integers[0].intValue());
                return favourite;
            }

            @Override
            protected void onPostExecute(MovieEntity movieEntities) {
                super.onPostExecute(movieEntities);
                if(movieEntities != null)
                    isFav = true;
                else
                    isFav = false;
            }
        }

        IsFavourites isFavourites = new IsFavourites();
        isFavourites.execute(id);
    }
}

