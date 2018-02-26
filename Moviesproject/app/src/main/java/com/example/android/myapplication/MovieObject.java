package com.example.android.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sadat on 2/26/18.
 */

public class MovieObject implements Parcelable {

    public static final Creator<MovieObject> CREATOR = new Creator<MovieObject>() {
        @Override
        public MovieObject createFromParcel(Parcel in) {
            return new MovieObject(in);
        }

        @Override
        public MovieObject[] newArray(int size) {
            return new MovieObject[size];
        }
    };
    private String movieName;
    private String imageURL;
    private String voteaverage;
    private String movieoverview;
    private String releasedate;
    private int id;

    /*
                String movietitle = currentMovie.getString(MDB_TITLE);
                String movieoverview = currentMovie.getString(MDB_OVERVIEW);
                String releasedate = currentMovie.getString(MDB_RELEASEDATE);
*/
    public MovieObject(String movieName, String imageURL, String voteaverage, String movieoverview, String releasedate, int id) {
        this.movieName = movieName;
        this.imageURL = imageURL;
        this.voteaverage = voteaverage;
        this.movieoverview = movieoverview;
        this.releasedate = releasedate;
        this.id = id;
    }

    protected MovieObject(Parcel in) {
        movieName = in.readString();
        imageURL = in.readString();
        voteaverage = in.readString();
        movieoverview = in.readString();
        releasedate = in.readString();
        id = in.readInt();
    }

    public String getMovieName() {
        return movieName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getVoteAverage() {
        return voteaverage;
    }

    public String getMovieOverview() {
        return movieoverview;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieName);
        dest.writeString(imageURL);
        dest.writeString(voteaverage);
        dest.writeString(movieoverview);
        dest.writeString(releasedate);
        dest.writeInt(id);
    }
}

