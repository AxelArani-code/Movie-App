package com.example.movieplus.Model;

import java.util.List;

public class PersonResponseResults {

    private Double popularity;
    private Integer id ;
    private String profile_path;
    private String name;
    private List<PersonResponseResultsKnewnFor> known_for;
    private boolean adult;

    public PersonResponseResults() {

    }

    public PersonResponseResults(Double popularity, Integer id, String profile_path, String name, List<PersonResponseResultsKnewnFor> known_for, boolean adult) {
        this.popularity = popularity;
        this.id = id;
        this.profile_path = profile_path;
        this.name = name;
        this.known_for = known_for;
        this.adult = adult;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfile_path() {
        //Create a baseUrl fro this poster
        String baseUrl = "https://image.tmdb.org/t/p/w500";
        return baseUrl + profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonResponseResultsKnewnFor> getKnown_for() {
        return known_for;
    }

    public void setKnown_for(List<PersonResponseResultsKnewnFor> known_for) {
        this.known_for = known_for;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }
}
