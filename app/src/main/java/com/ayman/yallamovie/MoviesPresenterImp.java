package com.ayman.yallamovie;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Fatima on 2019-02-05.
 */

public class MoviesPresenterImp implements MoviesPresenter{

    private static final String TAG = "MoviesPresenterImp";

    private MoviesView mView;
    private MoviesRepository mRepository;
    private MoviesListAdapter mAdapter;


    public MoviesPresenterImp(MoviesView mView) {
        this.mView = mView;
        mRepository = new MoviesRepositoryImp();
        //onMovieSelectedListener left out
        mAdapter = new MoviesListAdapter(mRepository.getMovies(), mView);
    }

    @Override
    public void loadMovies() {
        List<MovieInformation> availableMovies = mRepository.loadMovies();
        mAdapter.replaceData(availableMovies);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }
}
