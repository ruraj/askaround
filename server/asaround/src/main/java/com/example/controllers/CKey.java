package com.example.controllers;

import com.example.firebase.Main;
import com.example.models.Key;
import com.example.models.Location;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

/**
 * Created by sibi on 10/2/16.
 */
@RestController
public class CKey {

    @RequestMapping("/key")
    public boolean key(Key key) {
        if(Main.locations.get(key.getUsername()) == null) {
            String un = generateString();
            while(Main.publicnames.get(un) != null)
                un = generateString();
            Main.usernames.put(un, key.getUsername());
            Main.publicnames.put(key.getUsername(), un);
        }
        Main.keys.put(key.getUsername(), key.getKey());
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
