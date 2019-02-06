package com.ayman.yallamovie;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Fatima on 2019-02-05.
 */

public class MoviesListAdapter extends RecyclerView.Adapter<MovieViewHolder>  {

    private static final String TAG = "HomeListAdapter";

    private List<MovieInformation> mMovies;
    private MoviesView mContext;
    //private final onMovieSelectedListener mListener; // This is the link between The Fragment and the Adapter.

    public MoviesListAdapter(List<MovieInformation> mMovies, MoviesView mContext) {
        this.mMovies = mMovies;
        this.mContext = mContext;

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie,parent,false);
        //on movieselected taken out
        final MovieViewHolder viewHolder = new MovieViewHolder(rowView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        if(mMovies != null)
        {
            final MovieInformation info = mMovies.get(position);
            Log.d(TAG, "onBindViewHolderabc: " + info.getTitle());

            holder.setTitle(info.getTitle());
        }


    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        } else {
            return 0;
        }
    }

    public void replaceData(List<MovieInformation> availableMovies) {
        mMovies = availableMovies;
    }
}
