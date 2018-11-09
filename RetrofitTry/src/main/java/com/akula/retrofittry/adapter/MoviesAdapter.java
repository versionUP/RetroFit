package com.akula.retrofittry.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akula.retrofittry.R;
import com.akula.retrofittry.model.Movie;

import java.util.List;

public class MoviesAdapter  extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private List<Movie> movies;
    private int rowLayout;
    private Context context;

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieViewHolder holder, int position) {

        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());

    }

    @Override
    public int getItemCount() {
        return movies==null ? 0:movies.size();
    }



    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        public MovieViewHolder(@NonNull View v) {
            super(v);

            moviesLayout = (LinearLayout) v.findViewById(R.id.movie_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
        }
    }
}
