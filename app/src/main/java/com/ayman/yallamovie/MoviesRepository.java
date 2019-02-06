package com.ayman.yallamovie;

import java.util.List;

/**
 * Created by Fatima on 2019-02-05.
 */

public interface MoviesRepository {

    List<MovieInformation> loadMovies();

    List<MovieInformation> getMovies();
}
