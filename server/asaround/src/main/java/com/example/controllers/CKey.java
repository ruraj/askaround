package com.example.controllers;

import com.example.firebase.Main;
import com.example.models.Key;
import com.example.models.Location;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sibi on 10/2/16.
 */
@RestController
public class CKey {

    @RequestMapping("/key")
    public boolean key(Key key) {
        Main.keys.put(key.getUsername(), key.getKey());
        return true;
    }
}
