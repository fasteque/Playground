package com.fasteque.playground.model.entities;

import java.util.List;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public class TvShowDetail {
    /*
    {"backdrop_path":null,"created_by":[],"episode_run_time":[],
    "first_air_date":"2015-05-25",
    "genres":[],
    "homepage":"http://www.foxplaybrasil.com.br/show/12003-lucky-ladies",
    "id":62723,"in_production":true,
    "languages":["pt"],"last_air_date":"2015-05-25","name":"Lucky Ladies",
    "networks":[{"id":1117,"name":"Fox Life (Brasil)"}],
    "number_of_episodes":null,"number_of_seasons":1,
    "origin_country":["BR"],"original_language":"pt",
    "original_name":"Lucky Ladies","overview":"",
    "popularity":4.21875,"poster_path":"/1hNGcMomOJMAt2223LOACaFj0AB.jpg",
    "production_companies":[],
    "seasons":[{"air_date":"2015-05-25","episode_count":1,"id":66905,"poster_path":null,"season_number":1}],
    "status":"Returning Series","type":"Reality","vote_average":0.0,"vote_count":0}
    */

    private String backdrop_path;
    // TODO: created_by
    // TODO: episode_run_time
    private String first_air_date;
    // TODO: genres
    private String homepage;
    private Number id;
    private boolean in_production;
    private List<String> languages;
    private String last_air_date;
    private String name;
    // TODO: networks
    private Number number_of_episodes;
    private Number number_of_seasons;
    private List<String> origin_country;
    private String original_language;
    private String original_name;
    private String overview;
    private Number popularity;
    private String poster_path;
    // TODO: production_companies;
    // TODO: seasons
    private String status;
    private String type;
    private Number vote_average;
    private Number vote_count;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public boolean isIn_production() {
        return in_production;
    }

    public void setIn_production(boolean in_production) {
        this.in_production = in_production;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(Number number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public Number getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(Number number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Number getPopularity() {
        return popularity;
    }

    public void setPopularity(Number popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Number getVote_average() {
        return vote_average;
    }

    public void setVote_average(Number vote_average) {
        this.vote_average = vote_average;
    }

    public Number getVote_count() {
        return vote_count;
    }

    public void setVote_count(Number vote_count) {
        this.vote_count = vote_count;
    }
}
