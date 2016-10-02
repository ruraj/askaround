package com.example.controllers;

import com.example.firebase.Main;
import com.example.models.Location;
import com.example.models.Question;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

/**
 * Created by sibi on 10/2/16.
 */
@RestController
public class CLocation {

    @RequestMapping("/location")
    public boolean location(Location location) {
        if(Main.locations.get(location.getUsername()) == null) {
            String un = generateString();
            while(Main.publicnames.get(un) != null)
                un = generateString();
            Main.usernames.put(un, location.getUsername());
            Main.publicnames.put(location.getUsername(), un);
        }
        double[] array = {location.getLat(), location.getLon()};
        Main.locations.put(location.getUsername(), array);
        return true;
    }

    public String generateString() {
        int len = 8;
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
