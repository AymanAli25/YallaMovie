package com.ayman.yallamovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by Fatima on 2019-02-08.
 */

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouriteViewHolder> {

    private Context mContext;
    private List<MovieEntity> favouritesList;
    FavouritesFragment favFrag;

    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    public FavouritesAdapter(Context context, List<MovieEntity> favList, FavouritesFragment parentFrag) {
        this.mContext = context;
        this.favouritesList = favList;
        this.favFrag = parentFrag;
    }

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_favourite, parent, false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        MovieEntity movie = favouritesList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return favouritesList.size();
    }

    class FavouriteViewHolder extends RecyclerView.ViewHolder{

        MovieEntity movieEntity;

        TextView title;
        ImageView poster;
        ImageButton remove;

        public FavouriteViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_fav_title);
            poster = itemView.findViewById(R.id.item_fav_poster);
            remove = itemView.findViewById(R.id.item_fav_remove);

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RemoveFavouriteTask removeFavouriteTask = new RemoveFavouriteTask(mContext);
                    removeFavouriteTask.execute(movieEntity);
                    favFrag.refresh();
                }
            });
        }


        public void bind(MovieEntity movieEntity) {
            this.movieEntity = movieEntity;
            title.setText(movieEntity.getTitle());
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + movieEntity.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster);
        }
    }
}
