package com.ayman.yallamovie;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Fatima on 2019-02-05.
 */

public interface MoviesPresenter {

    void loadMovies();

    RecyclerView.Adapter getAdapter();
}
