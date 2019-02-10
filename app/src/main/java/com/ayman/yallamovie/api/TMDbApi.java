package com.ayman.yallamovie.api;

import com.ayman.yallamovie.api.response.CastResponse;
import com.ayman.yallamovie.api.response.GenresResponse;
import com.ayman.yallamovie.api.data.Movie;
import com.ayman.yallamovie.api.response.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ayman on 2019-02-07.
 */

public interface TMDbApi {

    @GET("movie/now_playing")
    Call<MoviesResponse> getLatestMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("genre/movie/list")
    Call<GenresResponse> getGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apiKEy,
            @Query("language") String language
    );

    @GET("search/movie")
    Call<MoviesResponse> searchMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") int page
    );

    @GET("movie/{movie_id}/similar")
    Call<MoviesResponse> getSimilarMovies (
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("movie/{movie_id}/credits")
    Call<CastResponse> getCast(
            @Path("movie_id") int id,
            @Query("api_key") String apiKEy
    );
}
