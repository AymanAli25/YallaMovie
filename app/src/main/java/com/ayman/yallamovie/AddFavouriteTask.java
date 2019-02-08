package com.ayman.yallamovie;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by Fatima on 2019-02-08.
 */

public class AddFavouriteTask extends AsyncTask<MovieEntity, Void, Void> {

    private WeakReference<Context> mContext;

    public AddFavouriteTask(Context context) {
        mContext = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(MovieEntity... movieEntities) {
        Context context = mContext.get();
        //adding to database
        DatabaseClient.getInstance(context).getAppDatabase()
                .movieDAO()
                .insert(movieEntities[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Context context = mContext.get();
        Toast.makeText(context, "Added to favourites.", Toast.LENGTH_SHORT).show();
    }
}
