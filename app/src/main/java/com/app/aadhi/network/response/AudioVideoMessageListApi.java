package com.app.aadhi.network.response;

import com.app.aadhi.dashboard.videomsg.VideoBinder;

import java.util.List;

public class AudioVideoMessageListApi {

    public String message;

    public List<AudioVideoMessageListApi.Data> data;

    public boolean success;

    /**
     * Data Class
     */
    public static class Data {
        public int id;

        public String title;

        public transient VideoBinder binder = new VideoBinder(this);
    }
}
