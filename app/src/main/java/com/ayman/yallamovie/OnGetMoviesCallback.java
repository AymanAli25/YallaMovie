package com.ayman.yallamovie;

import java.util.List;

/**
 * Created by Fatima on 2019-02-07.
 */

public interface OnGetMoviesCallback {

    void onSuccess(List<Movie> movies);

    void onError();
}
