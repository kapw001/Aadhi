package com.app.aadhi.dashboard.matrimony;

public class MatrimonyEmailData {

    public UserDetails userDetails;
    public PreferenceDails preferenceDails;

    public MatrimonyEmailData(UserDetails userDetails, PreferenceDails preferenceDails) {
        this.userDetails = userDetails;
        this.preferenceDails = preferenceDails;
    }

    public static class UserDetails {
        public String userGender;
        public String subSect;
        public String gothram;
        public String dateOfBrith;
        public String timeOfBirth;
        public String qualification;
        public String profession;
        public String address;
        public String whatsappNo;
        public String fathersNameAndAge;
        public String mothersNameAndAge;
        public String noOfBrothers;
        public String elderYoungerBrothers;
        public String noOfSisters;
        public String elderYoungerSisters;
    }
    
    public static class PreferenceDails {
        public String preferenceGender;
        public String preferAgeUpto;
        public String preferEducation;
        public String preferNativity;
        public String preferWorkingType;
        public String preferIndiaOrOverseas;
    }
}
