package com.example.movie_mania;


import java.util.List;

public interface OnGetMoviesCallback {

    void onSuccess(List<Movie> movies);
    void onError();
}
