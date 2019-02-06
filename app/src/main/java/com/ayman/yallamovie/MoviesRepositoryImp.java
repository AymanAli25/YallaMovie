package com.ayman.yallamovie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Fatima on 2019-02-05.
 */

public class MoviesRepositoryImp implements MoviesRepository{

    private List<MovieInformation> movies;


    public MoviesRepositoryImp()
    {
        movies = new ArrayList<MovieInformation>();
    }

    @Override
    public List<MovieInformation> loadMovies() {
        MovieInformation temp = new MovieInformation();
        temp.setTitle("Hello");

        List<MovieInformation> temp2 = new ArrayList<MovieInformation>();
        temp2.add(temp);

        movies.add(temp);

        return temp2;
    }

    @Override
    public List<MovieInformation> getMovies() {
        return movies;
    }


}
