package com.fasteque.playground.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public class TvShow implements Parcelable {
    private String backdrop_path;
    private String first_air_date;
    private Number id;
    private String name;
    private List<String> origin_country;
    private String original_name;
    private Number popularity;
    private String poster_path;
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

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(backdrop_path);
        dest.writeString(first_air_date);
        dest.writeLong(id.longValue());
        dest.writeString(name);
        dest.writeStringList(origin_country);
        dest.writeString(original_name);
        dest.writeLong(popularity.longValue());
        dest.writeString(poster_path);
        dest.writeLong(vote_average.longValue());
        dest.writeLong(vote_count.longValue());
    }

    public static final Parcelable.Creator<TvShow> CREATOR
            = new Parcelable.Creator<TvShow>() {
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    private TvShow(Parcel in) {
        backdrop_path = in.readString();
        first_air_date = in.readString();
        id = in.readLong();
        name = in.readString();
        origin_country = new ArrayList<String>();
        in.readStringList(origin_country);
        original_name = in.readString();
        popularity = in.readLong();
        poster_path = in.readString();
        vote_average = in.readLong();
        vote_count = in.readLong();
    }
}
