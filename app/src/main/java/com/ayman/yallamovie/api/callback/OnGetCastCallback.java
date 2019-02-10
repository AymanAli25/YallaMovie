package com.ayman.yallamovie.api.callback;

import com.ayman.yallamovie.api.data.Cast;

import java.util.List;

/**
 * Created by Fatima on 2019-02-09.
 */

public interface OnGetCastCallback {
    void onSuccess(List<Cast> cast);

    void onError();
}
