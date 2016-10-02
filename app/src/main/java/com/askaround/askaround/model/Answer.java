package com.askaround.askaround.model;

/**
 * Created by sibi on 10/2/16.
 */
public class Answer {
    private String publicName;
    private int questionId;
    private String response;

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
