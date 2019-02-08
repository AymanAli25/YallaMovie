package com.ayman.yallamovie.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayman.yallamovie.R;
import com.ayman.yallamovie.database.DatabaseClient;
import com.ayman.yallamovie.database.MovieEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavouritesFragment extends Fragment {

    private View mRootView;
    @BindView(R.id.recyclerview_favourites) RecyclerView favouritesList;

    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_favourites, container, false);
        ButterKnife.bind(this, mRootView);

        //Setup adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        favouritesList.setLayoutManager(layoutManager);

        getFavourites();

        return mRootView;
    }


    private void getFavourites() {
        class GetFavourites extends AsyncTask<Void, Void, List<MovieEntity>> {

            @Override
            protected List<MovieEntity> doInBackground(Void... voids) {
                List<MovieEntity> favsList = DatabaseClient
                        .getInstance(getContext())
                        .getAppDatabase()
                        .movieDAO()
                        .getAll();
                return favsList;
            }

            @Override
            protected void onPostExecute(List<MovieEntity> movieEntities) {
                super.onPostExecute(movieEntities);
                FavouritesAdapter adapter = new FavouritesAdapter(getContext(), movieEntities, FavouritesFragment.this);
                favouritesList.setAdapter(adapter);
            }
        }

        GetFavourites getFavourites = new GetFavourites();
        getFavourites.execute();
    }

    public void refresh()
    {
        getFavourites();
    }
}
