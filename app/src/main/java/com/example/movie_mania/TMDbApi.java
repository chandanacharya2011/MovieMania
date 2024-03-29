package com.example.movie_mania;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDbApi {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies (
            @Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page
    );

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies (
            @Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page
    );

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies (
            @Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page
    );

    @GET("genre/movie/list")
    Call<GenresResponse> getGenres (
            @Query("api_key") String apiKey, @Query("language") String language
    );

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getTrailers(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
