package com.app.aadhi.network.response;

import java.util.List;

public class CurrentEventsListApi {
    public String message;

    public OuterData data;

    public boolean success;

    /**
     * OuterData Class, that holds a list of DataItems
     */
    public static class OuterData {

        public List<InnerData> data;
    }

    /**
     * InnerData Class, is the Actual Data Item
     */
    public static class InnerData {

        public int id;

        public String title;

    }
}
