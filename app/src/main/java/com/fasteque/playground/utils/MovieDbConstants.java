package com.fasteque.playground.utils;

/**
 * Created by danielealtomare on 08/06/15.
 * Project: Playground
 */
public final class MovieDbConstants {

    private MovieDbConstants() {

    }

    private static String BASIC_STATIC_URL = "";
    private static String BACKDROP_PREFERRED_SIZE = "";
    private static String POSTER_PREFERRED_SIZE = "";

    public static String getBasicStaticUrl() {
        return BASIC_STATIC_URL;
    }

    public static void setBasicStaticUrl(String basicStaticUrl) {
        BASIC_STATIC_URL = basicStaticUrl;
    }

    public static String getBackdropPreferredSize() {
        return BACKDROP_PREFERRED_SIZE;
    }

    public static void setBackdropPreferredSize(String backdropPreferredSize) {
        BACKDROP_PREFERRED_SIZE = backdropPreferredSize;
    }

    public static String getPosterPreferredSize() {
        return POSTER_PREFERRED_SIZE;
    }

    public static void setPosterPreferredSize(String posterPreferredSize) {
        POSTER_PREFERRED_SIZE = posterPreferredSize;
    }
}
