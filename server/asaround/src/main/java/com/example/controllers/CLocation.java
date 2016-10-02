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
        double[] array = {location.getLat(), location.getLon()};
        Main.locations.put(location.getUsername(), array);
        return true;
    }
}
