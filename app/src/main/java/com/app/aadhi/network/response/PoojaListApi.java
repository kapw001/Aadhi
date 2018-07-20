package com.app.aadhi.network.response;

import java.util.List;

/**
 * Model Class for Parsing PoojaListApi Api Service
 */
public class PoojaListApi {

    public String message;

    public List<Data> data;

    public boolean success;

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", data = " + data + ", success = " + success + "]";
    }

    /**
     * Data Class
     */
    public static class Data {
        public String id;

        public String title;

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "ClassPojo [id = " + id + ", title = " + title + "]";
        }
    }
}
