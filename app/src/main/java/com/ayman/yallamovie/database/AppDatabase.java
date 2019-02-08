package com.ayman.yallamovie.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Ayman on 2019-02-08.
 */

@Database(entities = {MovieEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDAO movieDAO();
}