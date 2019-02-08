package com.ayman.yallamovie.api.callback;

import com.ayman.yallamovie.api.data.Movie;

/**
 * Created by Ayman on 2019-02-07.
 */

public interface OnGetMovieCallback {

    void onSuccess(Movie movie);

    void onError();
}