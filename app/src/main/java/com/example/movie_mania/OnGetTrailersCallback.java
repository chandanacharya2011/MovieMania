package com.example.movie_mania;

import java.util.List;

public interface OnGetTrailersCallback {

    void onSuccess(List<Trailer> trailers);

    void onError();
}
