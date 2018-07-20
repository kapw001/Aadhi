package com.app.aadhi.network.response;

import java.io.Serializable;

/**
 * Created by administrator on 31/01/18.
 */

public class RegistrationResponse implements Serializable {

    private boolean error;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
