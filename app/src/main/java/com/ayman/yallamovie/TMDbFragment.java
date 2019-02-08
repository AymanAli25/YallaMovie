package com.ayman.yallamovie;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TMDbFragment extends Fragment {

    private View mRootView;

    @BindView(R.id.tmdb_image) ImageView tmdbImage;

    public TMDbFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_tmdb, container, false);
        ButterKnife.bind(this, mRootView);

        return mRootView;
    }


}
