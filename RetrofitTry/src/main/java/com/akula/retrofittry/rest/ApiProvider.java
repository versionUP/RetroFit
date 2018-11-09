package com.akula.retrofittry.rest;

import com.akula.retrofittry.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiProvider {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    // other annotations
    /*
    *
    *
    * @Query
    * @POST
    * @Header
    * @Body
    *
    * */
}
