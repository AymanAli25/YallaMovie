package com.ayman.yallamovie;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by Fatima on 2019-02-08.
 */

public class RemoveFavouriteTask extends AsyncTask<MovieEntity, Void, Void> {

    private WeakReference<Context> mContext;

    public RemoveFavouriteTask(Context context) {
        mContext = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(MovieEntity... movieEntities) {
        Context context = mContext.get();
        //adding to database
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
