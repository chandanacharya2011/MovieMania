package com.example.movie_mania;

public interface OnGetMovieCallback {

    void onSuccess(Movie movie);

    void onError();
}
