package com.app.aadhi.network.response;

public class SlokasMantrasDetailApi {
    public String message;

    public SlokasMantrasDetailApi.Data data;

    public boolean success;


    /**
     * Data Class
     */
    public static class Data {
        public String id;

        public String title;

        public String thumbnail;

        public String audio_file;

    }
}
