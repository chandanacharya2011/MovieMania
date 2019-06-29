package com.example.movie_mania;


//Create a Retrofit instance, here we call our endpoint and retrive the list of movies.

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String LANGUAGE = "en-US";

    private static MoviesRepository repository;
    private static MoviesResponse moviesResponse;

    private TMDbApi api;

    public MoviesRepository(TMDbApi api){
        this.api = api;
    }


    public static MoviesRepository getInstance(){
        if(repository == null){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

            repository = new MoviesRepository(retrofit.create(TMDbApi.class));
        }

        return repository;

    }

    public void getMovies(int page, final OnGetMoviesCallback onGetMoviesCallback){

        Log.d("Movies Repository", "Next Page =" +page);
        api.getPopularMovies(BuildConfig.TMDB_API_KEY, LANGUAGE, page).enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                if(response.isSuccessful()){
                     moviesResponse = response.body();
                    if(moviesResponse != null && moviesResponse.getMovies() != null){
                        onGetMoviesCallback.onSuccess(moviesResponse.getPage(), moviesResponse.getMovies());
                    }

                }else{
                    onGetMoviesCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                onGetMoviesCallback.onError();
            }
        });


    }

    public void getGenres(final OnGetGenresCallback callback) {
        api.getGenres(BuildConfig.TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<GenresResponse>() {
                    @Override
                    public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                        if (response.isSuccessful()) {
                            GenresResponse genresResponse = response.body();
                            if (genresResponse != null && genresResponse.getGenres() != null) {
                                callback.onSuccess(genresResponse.getGenres());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenresResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


}
