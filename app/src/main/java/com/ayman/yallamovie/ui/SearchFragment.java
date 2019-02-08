package com.ayman.yallamovie.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ayman.yallamovie.api.data.Genre;
import com.ayman.yallamovie.api.data.Movie;
import com.ayman.yallamovie.repository.MoviesRepository;
import com.ayman.yallamovie.api.callback.OnGetGenresCallback;
import com.ayman.yallamovie.api.callback.OnGetMoviesCallback;
import com.ayman.yallamovie.api.callback.OnMoviesClickCallback;
import com.ayman.yallamovie.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchFragment extends Fragment {

    @BindView(R.id.search_recycler) RecyclerView searchResults;
    @BindView(R.id.search) SearchView searchBar;
    @BindView(R.id.search_text) TextView searchText;

    private View mRootView;
    private MoviesAdapter adapter;
    private MoviesRepository moviesRepository;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        moviesRepository = MoviesRepository.getInstance();

        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, mRootView);

        //Setup adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        searchResults.setLayoutManager(layoutManager);

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchText.setVisibility(View.INVISIBLE);
                searchMovies(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return mRootView;
    }

    private void searchMovies(String query) {
        moviesRepository.searchMovie(query, new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                List<Genre> temp = new ArrayList<>();
                adapter = new MoviesAdapter(movies, temp , callback);
                searchResults.setAdapter(adapter);
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


    private void getGenres() {
        moviesRepository.getGenres(new OnGetGenresCallback() {
            @Override
            public void onSuccess(List<Genre> genres) {
                //searchMovies(genres);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

}
