package com.app.aadhi.network.response;

import java.util.List;

/**
 * Model Class for Parsing PoojaListApi Api Service
 */
public class PilgrimageToursListApi {

    public String message;

    public List<Data> data;

    public boolean success;

    /**
     * Data Class
     */
    public static class Data {
        public String id;

        public String title;

        public String sub_title;

        public String banner_image;
    }
}
