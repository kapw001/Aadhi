package com.app.aadhi.utils;

import com.app.aadhi.dashboard.matrimony.MatrimonyEmailData;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class EmailUtils {

    // NOTE : IT is mandatory to set this following settings. Refer the link below.
    // https://github.com/matomo-org/matomo/issues/8613
    private static final String APP_FROM_EMAIL_DISPLAY_NAME = "AADHI ANDROID APP";
    private static final String APP_FROM_EMAIL_ID = "aadhiapp@gmail.com";
    private static final String APP_FROM_EMAIL_PASSWORD = "newnew45";
    private static final String APP_TO_EMAIL__ID = "aadhiapp@gmail.com";

    public static Message buildEmailMessage(String subject, String messageBody) {
        Session session = createSessionObject(APP_FROM_EMAIL_ID, APP_FROM_EMAIL_PASSWORD);

        try {
            Message message = createMessage(subject, messageBody, session);
            return message;
        } catch (UnsupportedEncodingException | MessagingException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("APP_LOG: Error when creating instance of Message in EmailUtils. Check the arguments.");
        }
    }

    private static Session createSessionObject(final String username, final String password) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private static Message createMessage(String subject, String messageBody, Session session) throws MessagingException,
            UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(APP_FROM_EMAIL_ID, APP_FROM_EMAIL_DISPLAY_NAME));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(APP_TO_EMAIL__ID, APP_TO_EMAIL__ID));
        message.setSubject(subject);
        message.setText(messageBody);
        return message;
    }

    public static Message composePoojaDetailsEmail(String pooja, String nameOrFamily, String gothram,
                                                   String raasi, String whatsappNo, String mobileNo,
                                                   String description) {
        String subject = String.format("%s - For Name/Family : %s", pooja, nameOrFamily);
        String body = ("Gothram : " + gothram) +
                       "\n" +
                       "Raasi : " + raasi +
                       "\n" +
                       "WhatsappNo : " + whatsappNo +
                       "\n" +
                       "MobileNo : " + mobileNo +
                       "\n" +
                       "Description : " + description +

                       "\n";
        return buildEmailMessage(subject, body);
    }

    public static Message composeVastuDetailsEmail(String name, String address, String mobileNo, String place, String emiRates, String description) {
        String subject = "Vaastu Request : " + name;
        String body = ("Name : " + name) +
                "\n" +
                "Address : " + address +
                "\n" +
                "MobileNo : " + mobileNo +
                "\n" +
                "Place : " + place +
                "\n" +
                "EmiRates : " + emiRates +
                "\n" +
                "Description : " + description +
                "\n";
        return buildEmailMessage(subject, body);
    }

    public static Message composeAstrologicalDetailsEmail(String name, String starRaasi, String timeOfBirth, String dateOfBirth, String whatsAppNo, String mobileNo, String description) {
        String subject = "Astrological Request : " + name;
        String body = ("Name : " + name) +
                "\n" +
                "Star / Raasi : " + starRaasi +
                "\n" +
                "Time Of Birth : " + timeOfBirth +
                "\n" +
                "Date Of Birth : " + dateOfBirth +
                "\n" +
                "WhatsApp No : " + whatsAppNo +
                "\n" +
                "Mobile No : " + mobileNo +
                "\n" +
                "Description : " + description +
                "\n";
        return buildEmailMessage(subject, body);
    }

    public static Message composePoojaForFamilyEmail(String name, String address, String date, String whatsappNo, String mobileNo, String typeOfPooja, String description) {
        String subject = "Pooja For Family : " + name;
        String body = ("Name : " + name) +
                "\n" +
                "Address : " + address +
                "\n" +
                "Date : " + date +
                "\n" +
                "WhatsAppNo : " + whatsappNo +
                "\n" +
                "Mobile No : " + mobileNo +
                "\n" +
                "Type of pooja : " + typeOfPooja +
                "\n" +
                "Description : " + description +
                "\n";
        return buildEmailMessage(subject, body);
    }

    public static Message composePoojaForChildrenEmail(String name, String address, String date, String whatsappNo, String mobileNo, String typeOfPooja, String description) {
        String subject = "Pooja For Children : " + name;
        String body = ("Name : " + name) +
                "\n" +
                "Address : " + address +
                "\n" +
                "Date : " + date +
                "\n" +
                "WhatsAppNo : " + whatsappNo +
                "\n" +
                "Mobile No : " + mobileNo +
                "\n" +
                "Type of pooja : " + typeOfPooja +
                "\n" +
                "Description : " + description +
                "\n";
        return buildEmailMessage(subject, body);
    }

    public static Message composeHomamDetailsEmail(String name, String address, String date, String whatsappNo, String mobileNo, String typeOfPooja, String description) {
        String subject = "Pooja For Children : " + name;
        String body = ("Name : " + name) +
                "\n" +
                "Address : " + address +
                "\n" +
                "Date : " + date +
                "\n" +
                "WhatsAppNo : " + whatsappNo +
                "\n" +
                "Mobile No : " + mobileNo +
                "\n" +
                "Type Of Pooja : " + typeOfPooja +
                "\n" +
                "Description : " + description +
                "\n";
        return buildEmailMessage(subject, body);
    }

    public static Message composeSrathamThithiEmail(String name, String gothramThithi, String date, String whatsappNo, String mobileNo, String typeOfPooja, String description) {
        String subject = "Pooja For Children : " + name;
        String body = ("Name : " + name) +
                "\n" +
                "Gothram / Thithi : " + gothramThithi +
                "\n" +
                "Date : " + date +
                "\n" +
                "WhatsAppNo : " + whatsappNo +
                "\n" +
                "Mobile No : " + mobileNo +
                "\n" +
                "Type Of Pooja : " + typeOfPooja +
                "\n" +
                "Description : " + description +
                "\n";
        return buildEmailMessage(subject, body);
    }

    public static Message composePilgrimageTourDetailsEmail(String name, String date, String whatsappNo, String description) {
        String subject = "Pooja For Children : " + name;
        String body = ("Name : " + name) +
                "\n" +
                "Date : " + date +
                "\n" +
                "WhatsAppNo : " + whatsappNo +
                "\n" +
                "Description : " + description +
                "\n";
        return buildEmailMessage(subject, body);
    }

    public static Message composeMatrimonyDetailsEmail(MatrimonyEmailData emailDetails) {
        String subject = "Aadhi Matrimony Service";
        String body = ("User Gender : " + emailDetails.userDetails.userGender) +
                "\n" +"Sub sect : " + emailDetails.userDetails.subSect +
                "\n" +"Gothram : " + emailDetails.userDetails.gothram +
                "\n" +"Date Of Birth : " + emailDetails.userDetails.dateOfBrith +
                "\n" +"Time Of Birth : " + emailDetails.userDetails.timeOfBirth +
                "\n" +"Qualification : " + emailDetails.userDetails.qualification +
                "\n" +"Profession : " + emailDetails.userDetails.profession +
                "\n" +"Address : " + emailDetails.userDetails.address +
                "\n" +"Whatsapp Contact No : " + emailDetails.userDetails.whatsappNo +
                "\n" +"Fathers Name & Age : " + emailDetails.userDetails.fathersNameAndAge +
                "\n" +"Mothers Name & Age : " + emailDetails.userDetails.mothersNameAndAge +
                "\n" +"No of Brothers : " + emailDetails.userDetails.noOfBrothers +
                "\n" +"Elder / Younger Brothers : " + emailDetails.userDetails.elderYoungerBrothers +
                "\n" +"No of Sisters : " + emailDetails.userDetails.noOfSisters +
                "\n" +"Elder / Younger Sisters : " + emailDetails.userDetails.elderYoungerSisters +


                "\n" +"Preferrence Gender : " + emailDetails.preferenceDails.preferenceGender +
                "\n" +"Age Up To : " + emailDetails.preferenceDails.preferAgeUpto +
                "\n" +"Education : " + emailDetails.preferenceDails.preferEducation +
                "\n" +"Nativity : " + emailDetails.preferenceDails.preferNativity +
                "\n" +"Working Preference : " + emailDetails.preferenceDails.preferWorkingType +
                "\n" +"India / Overseas : " + emailDetails.preferenceDails.preferIndiaOrOverseas +
                "\n";
        return buildEmailMessage(subject, body);
    }
}
