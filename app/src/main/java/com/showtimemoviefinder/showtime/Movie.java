package com.showtimemoviefinder.showtime;

import org.json.JSONObject;

public class Movie {
    private String movie_name;
    private String movie_description;
    private String movie_genre;
    private String top_cast;
    private String director;
    private String rating;
    private JSONObject showtime;

    public Movie(String mn, String md, String mg, String tc, String d, String r, JSONObject s) {
        movie_description = md;
        movie_genre = mg;
        movie_name = mn;
        top_cast = tc;
        director = d;
        rating = r;
        showtime = s;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public String getMovie_description() {
        return movie_description;
    }

    public String getDirector() {
        return director;
    }

    public JSONObject getShowtime() {
        return showtime;
    }

    public String getMovie_genre() {
        return movie_genre;
    }

    public String getRating() {
        return rating;
    }

    public String getTop_cast() {
        return top_cast;
    }

}
