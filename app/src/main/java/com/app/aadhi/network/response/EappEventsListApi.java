package com.app.aadhi.network.response;

import java.util.List;

public class EappEventsListApi {
    public String message;

    public List<Data> data;

    public boolean success;


    /**
     * Data Class
     */
    public static class Data {
        public int id;

        public String title;

        public String thumbnail;

    }
}
