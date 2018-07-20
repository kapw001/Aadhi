package com.app.aadhi.network.response;

import java.util.ArrayList;
import java.util.List;

public class CurrentEventsDetailsApi {
    public String message;

    public Data data;

    public boolean success;

    /**
     * OuterData Class, that holds a list of DataItems
     */
    public static class Data {

        public Main main;

        public List<String> banners;

        public List<String> videos;

        public Data() {
            this.main = new Main();
            this.banners = new ArrayList<>();
            this.videos = new ArrayList<>();
        }
    }

    public static class Main {
        public int id;
        public String title;

        Main() {
            this.id = 0;
            this.title = "";
        }
    }
}
