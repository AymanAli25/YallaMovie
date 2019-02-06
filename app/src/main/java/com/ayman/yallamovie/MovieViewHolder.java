package com.ayman.yallamovie;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Fatima on 2019-02-05.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder implements MiniView{

    private static final String TAG = "MovieViewHolder";

    @BindView(R.id.tv_title) TextView title;

    public MovieViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: i am clicked" );
    }

    //getter
    public TextView getTitle() {
        return title;
    }

    //setters
    public void setTitle(String a_title) {
        title.setText(a_title);
    }
}
