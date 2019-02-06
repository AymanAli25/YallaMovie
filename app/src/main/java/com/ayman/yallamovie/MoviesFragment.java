package com.ayman.yallamovie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MoviesFragment extends Fragment implements MoviesView{

    private static final String TAG = "MoviesFragment";

    private View mRootView;
    private MoviesPresenter mPresenter;

    @BindView(R.id.movies_recyclerView) RecyclerView mRecyclerView;


    public MoviesFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, mRootView);

        setupMVP();

        //Setup adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mPresenter.getAdapter());

        mPresenter.loadMovies();
        //refreshListener();

        //showProgress();
        return mRootView;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Setup the model, view, presenter
     */
    private void setupMVP() {
        MoviesPresenterImp presenterImp = new MoviesPresenterImp(this);
        mPresenter = presenterImp;
    }

}
