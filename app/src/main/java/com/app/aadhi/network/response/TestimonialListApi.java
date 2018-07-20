package com.app.aadhi.network.response;

import java.util.List;

/**
 * Created by webnoodle on 6/29/2018.
 */

public class TestimonialListApi {

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
        public String name;
        public String email;
        public String content;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "ClassPojo [id = " + id + ", name = " + name + "+, email = " + email + "+, content = " + content + "]";
        }
    }
}
