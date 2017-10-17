package com.capgemini.movies.MyMovieDatabase.model;


public class Movie {

    private String title;
    private boolean watched;

    public Movie(String title, boolean watched) {
        this.title = title;
        this.watched = watched;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
