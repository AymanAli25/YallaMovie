package com.ayman.yallamovie.api.callback;

import com.ayman.yallamovie.api.data.Genre;

import java.util.List;

/**
 * Created by Ayman on 2019-02-07.
 */

public interface OnGetGenresCallback {

    void onSuccess(List<Genre> genres);

    void onError();
}
