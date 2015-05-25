package com.fasteque.playground.model.entities;

import java.util.List;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public class TvShowsWrapper {
    private int page;
    private List<TvShow> results;
    private int total_pages;
    private int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TvShow> getResults() {
        return results;
    }

    public void setResults(List<TvShow> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
