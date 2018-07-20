package com.app.aadhi.network.response;

public class EappEventsDetailsApi {
    public String message;

    public Data data;

    public boolean success;

    /**
     * OuterData Class, that holds a list of DataItems
     */
    public static class Data {

        public int id = -1;

        public String title = "";

        public String thumbnail = "";

        public String banner_image = "";

        public String audio_file = "";

        public String description = "";
    }
}
