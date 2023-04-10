package com.example.movieplus.Model;



import java.util.List;

public class MovieResponse {
    private int page;
    private int total_resultas;
    private int total_pages;
    private List<MovieResponseResults > results;
    //Create contrustor

    public MovieResponse() {

    }

    public MovieResponse(int page, int total_resultas, int total_pages, List<MovieResponseResults> results) {
        this.page = page;
        this.total_resultas = total_resultas;
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_resultas() {
        return total_resultas;
    }

    public void setTotal_resultas(int total_resultas) {
        this.total_resultas = total_resultas;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<MovieResponseResults> getResults() {
        return results;
    }

    public void setResults(List<MovieResponseResults> results) {
        this.results = results;
    }
}
