package com.example.firebase;

import com.google.firebase.database.ValueEventListener;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by sibi on 10/2/16.
 */
public class Main {

    public static HashMap<String, double[]> locations = new HashMap<>(); //username->lat, long
    public static HashMap<String, String> publicnames= new HashMap<>(); //username->publicname
    public static HashMap<String, String> usernames = new HashMap<>(); //publicname->username
    public static HashMap<String, String> keys = new HashMap<>();        //username->key
    public static String targetURL = "https://fcm.googleapis.com/fcm/send";

    public static void main(String[] args) {
    }

    public static String executeJSONPost(String targetURL, String json) {
        try {
            HttpClient httpClient    = HttpClientBuilder.create().build();
            HttpPost post          = new HttpPost(targetURL);
            StringEntity postingString = new StringEntity(json);//gson.tojson() converts your pojo to json
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            post.setHeader("Authorization", "key=AIzaSyC23-fKYNb-C_kJponsKg-s0YONh4UBzBM");
            HttpResponse response = httpClient.execute(post);
            System.out.println(response.toString());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String executePost(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
