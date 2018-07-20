package com.app.aadhi.network.response;

import java.util.List;

public class SlokaOfTheDayApi {
    public String message;

    public List<SlokaOfTheDayApi.Data> data;

    public boolean success;


    /**
     * Data Class
     */
    public static class Data {
        public String id;

        public String title;

        public String audio_file;
    }
}
