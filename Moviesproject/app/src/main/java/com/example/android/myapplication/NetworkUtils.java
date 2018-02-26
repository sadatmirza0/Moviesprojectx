package com.example.android.myapplication;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by sadat on 2/26/18.
 */

public class NetworkUtils {

    final static String BASE_URL = "https://api.themoviedb.org/3/movie";
    final static String API_KEY = "e6119fc0e6963d6ee2300a97c6d1cf22";
    final static String PARAM_QUERY = "api_key";

    public static URL buildUrl(String searchCriteria) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(searchCriteria)
                .appendQueryParameter(PARAM_QUERY, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

/*    public static List<MovieObject> getMovieData(String input) throws JSONException {
        List<MovieObject> mList = new ArrayList<MovieObject>();
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
            MovieObject M =new MovieObject(movietitle, moviePosterURL, voteaverage, movieoverview, releasedate, movieID);
            mList.add(M);
            }
            return mList;
    }*/
}