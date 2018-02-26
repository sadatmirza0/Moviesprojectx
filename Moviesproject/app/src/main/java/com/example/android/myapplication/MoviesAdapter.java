package com.example.android.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sadat on 2/26/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private List<MovieObject> moviesList;
    private Context context;

    public MoviesAdapter(List<MovieObject> movies, Context context) {
        this.moviesList = movies;
        this.context = context;
    }

    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutIdForListItem = R.layout.movie_list_item;
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapterViewHolder holder, int position) {

        MovieObject movie = moviesList.get(position);

        holder.mMovieTextView.setText(movie.getMovieName());
        Picasso.with(context)
                .load(movie.getImageURL())
                .into(holder.mMovieImageView);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView mMovieImageView;
        public final TextView mMovieTextView;

        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);//new change
            mMovieImageView = itemView.findViewById(R.id.iv_movie);
            mMovieTextView = itemView.findViewById(R.id.tv_movie_name);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
