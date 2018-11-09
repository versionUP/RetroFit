package com.akula.retrofittry.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.akula.retrofittry.R;
import com.akula.retrofittry.adapter.MoviesAdapter;
import com.akula.retrofittry.model.Cinema;
import com.akula.retrofittry.model.CinemaResponse;
import com.akula.retrofittry.rest.ApiClient;
import com.akula.retrofittry.rest.ApiProvider;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "yourAPikEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(API_KEY .isEmpty()) return;
        ApiProvider apiProvider = ApiClient.getClient().create(ApiProvider.class);

        retrofit2.Call<CinemaResponse> call = apiProvider.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<CinemaResponse>() {
            @Override
            public void onResponse(retrofit2.Call<CinemaResponse> call, Response<CinemaResponse> response) {
                List<Cinema> cinemas = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(cinemas,R.layout.list_item_movie,getApplicationContext()));
            }

            @Override
            public void onFailure(retrofit2.Call<CinemaResponse> call, Throwable t) {

            }
        });
    }
}
