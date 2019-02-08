package com.ayman.yallamovie.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ayman.yallamovie.database.DatabaseClient;
import com.ayman.yallamovie.database.MovieEntity;

import java.lang.ref.WeakReference;

/**
 * Created by Ayman on 2019-02-08.
 */

public class RemoveFavouriteTask extends AsyncTask<MovieEntity, Void, Void> {

    private WeakReference<Context> mContext;

    public RemoveFavouriteTask(Context context) {
        mContext = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(MovieEntity... movieEntities) {
        Context context = mContext.get();
        //removing to database
        DatabaseClient.getInstance(context).getAppDatabase()
                .movieDAO()
                .delete(movieEntities[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Context context = mContext.get();
        Toast.makeText(context, "Removed from favourites.", Toast.LENGTH_SHORT).show();
    }
}
