package com.akula.retrofittry.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.akula.retrofittry.R;
import com.akula.retrofittry.adapter.MoviesAdapter;
import com.akula.retrofittry.model.Movie;
import com.akula.retrofittry.model.MovieResponse;
import com.akula.retrofittry.rest.ApiClient;
import com.akula.retrofittry.rest.ApiProvider;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "4922d14e918b4a30c6991ff1817deb08";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(API_KEY .isEmpty()) return;
        ApiProvider apiProvider = ApiClient.getClient().create(ApiProvider.class);

        retrofit2.Call<MovieResponse> call = apiProvider.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies,R.layout.list_item_movie,getApplicationContext()));
            }

            @Override
            public void onFailure(retrofit2.Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}
