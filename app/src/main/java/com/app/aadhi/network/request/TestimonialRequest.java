package com.app.aadhi.network.request;

/**
 * Created by webnoodle on 6/29/2018.
 */

public class TestimonialRequest {


    public Data data;

    /**
     * Data Class
     */
    public static class Data {

        public String name;
        public String email;
        public String content;

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setContent(String content) {
            this.content = content;
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
            return "ClassPojo [name = " + name + "+, email = " + email + "+, content = " + content + "]";
        }
    }


}
