package com.ayman.yallamovie.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ayman.yallamovie.api.data.Genre;
import com.ayman.yallamovie.api.data.Movie;
import com.ayman.yallamovie.repository.MoviesRepository;
import com.ayman.yallamovie.api.callback.OnGetGenresCallback;
import com.ayman.yallamovie.api.callback.OnGetMoviesCallback;
import com.ayman.yallamovie.api.callback.OnMoviesClickCallback;
import com.ayman.yallamovie.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MoviesFragment extends Fragment {

    private static final String TAG = "MoviesFragment";

    private View mRootView;
    @BindView(R.id.movies_list) RecyclerView moviesList;

    private MoviesAdapter adapter;
    private MoviesRepository moviesRepository;
    private List<Genre> allGenres;

    public MoviesFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        moviesRepository = MoviesRepository.getInstance();

        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, mRootView);

        //Setup adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        moviesList.setLayoutManager(layoutManager);

        getGenres();
        getMovies();

        return mRootView;
    }


    private void getGenres() {
        moviesRepository.getGenres(new OnGetGenresCallback() {
            @Override
            public void onSuccess(List<Genre> genres) {
                allGenres = genres;
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    private void getMovies() {
        moviesRepository.getMovies(new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                adapter = new MoviesAdapter(movies, allGenres, callback);
                moviesList.setAdapter(adapter);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    OnMoviesClickCallback callback = new OnMoviesClickCallback() {
        @Override
        public void onClick(Movie movie) {
            Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
            intent.putExtra(MovieDetailsActivity.MOVIE_ID, movie.getId());
            startActivity(intent);
        }
    };

    private void showError() {
        Toast.makeText(getContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();
    }



}
