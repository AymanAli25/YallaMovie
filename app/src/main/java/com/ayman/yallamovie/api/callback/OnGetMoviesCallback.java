package com.ayman.yallamovie.api.callback;

import com.ayman.yallamovie.api.data.Movie;

import java.util.List;

/**
 * Created by Ayman on 2019-02-07.
 */

public interface OnGetMoviesCallback {

    void onSuccess(List<Movie> movies);

    void onError();
}
