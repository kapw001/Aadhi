package com.app.aadhi;

import java.util.ArrayList;
import java.util.List;

public class SessionStorage {

    private static SessionStorage sessionInstance = null;

    private String mRCUrl;
    private String mVehicleId;
    private List<String> primaryLanguages = new ArrayList<>();
    private List<String> secondaryLanguages = new ArrayList<>();

    private SessionStorage() {

    }

    public List<String> getPrimaryLanguages() {
        return primaryLanguages;
    }

    public void setPrimaryLanguages(List<String> primaryLanguages) {
        this.primaryLanguages = primaryLanguages;
    }

    public List<String> getSecondaryLanguages() {
        return secondaryLanguages;
    }

    public void setSecondaryLanguages(List<String> secondaryLanguages) {
        this.secondaryLanguages = secondaryLanguages;
    }

    public static SessionStorage getInstance() {
        if (sessionInstance == null) {
            sessionInstance = new SessionStorage();
        }

        return sessionInstance;
    }

    public String getRCUrl() {
        return mRCUrl;
    }

    public void setRCUrl(String mRCUrl) {
        this.mRCUrl = mRCUrl;
    }

    public String getVehicleId() {
        return mVehicleId;
    }

    public void setVehicleId(String mVehicleId) {
        this.mVehicleId = mVehicleId;
    }

}
