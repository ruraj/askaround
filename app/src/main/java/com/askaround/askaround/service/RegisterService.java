package com.askaround.askaround.service;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ruraj on 10/1/16.
 */
public class RegisterService extends AsyncTask<String, Void, Boolean> {

  @Override
  protected Boolean doInBackground(String... strings) {
    String email = strings[0];
    JSONObject emailJson = new JSONObject();
    try {
      emailJson.put("email", email);
    } catch (JSONException e) {
      e.printStackTrace();
      // TODO Something wrong? Handle this
    }

    try {
      final String url = Urls.REGISTER_URL;
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
      return restTemplate.postForObject(url, emailJson, Boolean.class);
    } catch (Exception e) {
      Log.e("RegisterService", e.getMessage(), e);
    }

    return null;
  }

  @Override
  protected void onPostExecute(Boolean aBoolean) {
    if (aBoolean) {
      // Show code entry box
    } else {
      // Show error box
    }
  }
}
