package com.ayman.yallamovie;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Fatima on 2019-02-08.
 */

@Dao
public interface MovieDAO {

    @Query("SELECT * FROM movieentity")
    List<MovieEntity> getAll();

    @Insert
    void insert(MovieEntity movieEntity);

    @Delete
    void delete(MovieEntity movieEntity);

    @Query("SELECT * FROM movieentity WHERE id = :movie_id")
    MovieEntity loadFavourite(int movie_id);

}
