package com.app.aadhi.network.response;

public class AudioVideoMessageDetailApi {
    public String message;

    public AudioVideoMessageDetailApi.Data data;

    public boolean success;


    /**
     * Data Class
     */
    public static class Data {
        public String id;

        public String title;

        public String audio_file;

        public String video_url;

    }
}
