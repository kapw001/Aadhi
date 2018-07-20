package com.app.aadhi.network.response;

import java.util.List;

public class SlokasMantrasListApi {
    public String message;

    public List<Data> data;

    public boolean success;

    /**
     * OuterData Class, that holds a list of DataItems
     */
    public static class Data {

        public int id;

        public String title;

        public String thumbnail;
    }

}
