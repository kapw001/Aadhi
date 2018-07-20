package com.app.aadhi.network.response;

public class PilgrimageTourDetailsApi {

    public String message;

    public PilgrimageTourDetailsApi.Data data;

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

        public String sub_title;

        public String description;

        public String banner_image;
    }
}
