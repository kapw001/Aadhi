package com.app.aadhi.network.response;

public class PoojaDetailApi {
    public String message;

    public Data data;

    public boolean success;

    public class Data {
        public String id;

        public String title;

        public String description;

        public String banner_image;

        public String sub_title;
    }
}
