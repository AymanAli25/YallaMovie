package com.ayman.yallamovie;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Fatima on 2019-02-08.
 */

@Database(entities = {MovieEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDAO movieDAO();
}