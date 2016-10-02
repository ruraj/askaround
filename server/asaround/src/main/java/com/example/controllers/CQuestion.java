package com.example.controllers;

import com.example.TestModel;
import com.example.firebase.Main;
import com.example.models.Question;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sibi on 10/1/16.
 */
@RestController
public class CQuestion {

    @RequestMapping("/question/new")
    public boolean question(Question question) {
        double radius = 5;
        String username = Main.keys.get(question.getUsername());
        double[] cloc = Main.locations.get(username);
        String regIds = "";
        for(Map.Entry entry : Main.keys.entrySet()) {
            if(entry.getKey() != username) {
                double[] loc = Main.locations.get(entry.getKey());
                if(loc == null)
                    continue;
                if(computeDistance(loc[0], loc[1], cloc[0], cloc[1]) < radius) {
                    if(regIds == "") regIds = "\"" + entry.getValue() + "\"";
                    else regIds += ",\"" + entry.getValue() + "\"";
                }
            }
        }

        regIds = "\"registration_ids\":[" + regIds + "]";

        String json = "{" + regIds + ",\"data\": {\"question\": \"" + question.getDescription() + "\", \"id\": \"" + question.getLat() + "\"}}";
        Main.executeJSONPost(Main.targetURL, json);
        return true;
    }

    public double computeDistance(double lat1, double long1, double lat2, double long2) {
        double pi = Math.PI;
        lat1 = lat1/pi;
        lat2 = lat2/pi;
        double dlat = Math.abs(lat2-lat1)/pi;
        double dlong = Math.abs(long2 - long1)/pi;
        double R = 6371 * 0.621371; //miles
        double a = Math.sin(dlat/2) * Math.sin(dlat/2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dlong/2) * Math.sin(dlong/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }
}
