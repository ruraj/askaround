package com.example.controllers;

import com.example.firebase.Main;
import com.example.models.Answer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sibi on 10/2/16.
 */
@RestController
public class CAnswer {

    @RequestMapping("/answer")
    public boolean answer(Answer answer) {
        String un = Main.usernames.get(answer.getPublicName());
        String key = Main.keys.get(un);
        String json = "{\"registration_ids\":[\"" + key + "\"],\"data\": {\"answer\": \""+ answer.getResponse() + "\", \"id\": \"" + answer.getQuestionId() + "\"}}";
        Main.executeJSONPost(Main.targetURL, json);
        return true;
    }
}
