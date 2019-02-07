package com.ayman.yallamovie;

import java.util.List;

/**
 * Created by Fatima on 2019-02-07.
 */

public interface OnGetGenresCallback {

    void onSuccess(List<Genre> genres);

    void onError();
}
