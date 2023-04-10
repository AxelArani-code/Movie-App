package com.example.movieplus.Model;

import java.util.List;

public class MovieResponseResults {
    private long cote_count;
    private int id;
    private boolean video;
    private float vote_average;
    private String title;
    private String poster_patch;
    private String original_language;
    private String original_title;
    private List<Integer> genre_ids;
    private String backdrop_path;
    private boolean adult;
    private String overview;
    private String release_date;
    //new Create Contructor

    public MovieResponseResults() {

    }

    public MovieResponseResults(long cote_count, int id, boolean video, float vote_average, String title, String poster_patch, String original_language, String original_title, List<Integer> genre_ids, String backdrop_path, boolean adult, String overview, String release_date) {
        this.cote_count = cote_count;
        this.id = id;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.poster_patch = poster_patch;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
    }

    public long getCote_count() {
        return cote_count;
    }

    public void setCote_count(long cote_count) {
        this.cote_count = cote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_patch() {
        //Create a baseUrl fro this poster
        String baseUrl = "https://image.tmdb.org/t/p/w500";
        return baseUrl + poster_patch;
    }

    public void setPoster_patch(String poster_patch) {
        this.poster_patch = poster_patch;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
