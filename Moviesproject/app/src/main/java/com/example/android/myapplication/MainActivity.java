package com.example.android.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    private final String PATH_POPULAR = "popular";
    private final String PATH_TOPRATED = "movie/top_rated?";
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter movieAdapter;
    private List<MovieObject> listOfMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URL searchUrl = NetworkUtils.buildUrl(PATH_POPULAR);
        new FetchMoviesTask().execute(searchUrl);

        myRecyclerView = findViewById(R.id.RecyclerView_main);
        myRecyclerView.setHasFixedSize(true);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);

        myRecyclerView.setLayoutManager(layoutManager);
    }

    public List<MovieObject> getMovieData(String input) throws JSONException {
        List<MovieObject> mList = new ArrayList<>();
        final String MDB_RESULT = "results";
        final String MDB_TITLE = "title";
        final String MDB_POSTER = "poster_path";
        final String MDB_OVERVIEW = "overview";
        final String MDB_RATING = "vote_average";
        final String MDB_RELEASEDATE = "release_date";
        JSONObject moviejson = new JSONObject(input);
        JSONArray movieArray = moviejson.getJSONArray(MDB_RESULT);
        String baseURL = "http://image.tmdb.org/t/p/w500/";

        for (int i = 0; i < movieArray.length(); i++) {

            JSONObject currentMovie = movieArray.getJSONObject(i);
            String voteaverage = currentMovie.getString(MDB_RATING);
            int movieID = Integer.parseInt(currentMovie.getString("id"));
            String movietitle = currentMovie.getString(MDB_TITLE);
            String movieoverview = currentMovie.getString(MDB_OVERVIEW);
            String releasedate = currentMovie.getString(MDB_RELEASEDATE);
            String moviePosterendURL = currentMovie.getString(MDB_POSTER);
            String moviePosterURL = baseURL + moviePosterendURL;
            MovieObject M = new MovieObject(movietitle, moviePosterURL, voteaverage, movieoverview, releasedate, movieID);
            mList.add(M);
        }
        return mList;
    }

    public class FetchMoviesTask extends AsyncTask<URL, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Hold on.....");
            dialog.show();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL inputURL = urls[0];
            String jsonResponse = new String();

            try {
                jsonResponse = NetworkUtils.getResponseFromHttpUrl(inputURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String response) {
            listOfMovies = new ArrayList<>();

            try {
                listOfMovies = getMovieData(response);
                dialog.dismiss();
                Log.v("MainActivity", "Sadats" + listOfMovies);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            movieAdapter = new MoviesAdapter(listOfMovies, MainActivity.this);
            movieAdapter.notifyDataSetChanged();
            myRecyclerView.setAdapter(movieAdapter);

        }
    }

}