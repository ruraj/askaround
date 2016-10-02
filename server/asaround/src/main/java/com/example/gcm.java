package com.example;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sibi on 10/1/16.
 */
public class gcm {
    public static final String GCM_API_KEY = "Get this API key from Google Developer Console";
    public static final String MESSAGE_VALUE = "Hello, Sending Notifications using GCM";
    public static final String MESSAGE_KEY = "message";
    public static final String REG_ID = "This you'll get once you register on GCM";

    public static void main(String[] args) throws IOException {


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("/home/sibi/IdeaProjects/asaround/askaround-7f21b9e21a9e.json"))
                .setDatabaseUrl("https://askaround-145201.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);
    }
}