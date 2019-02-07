package com.ayman.yallamovie;

/**
 * Created by Fatima on 2019-02-07.
 */

public interface OnGetMovieCallback {

    void onSuccess(Movie movie);

    void onError();
}