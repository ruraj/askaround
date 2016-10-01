package com.askaround.askaround.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ruraj on 10/1/16.
 */
public class VerifyService extends AsyncTask<String, Void, Boolean> {

  private Context context;

  public VerifyService(Context context) {
    this.context = context;
  }

  @Override
  protected Boolean doInBackground(String... strings) {
    String token = strings[0];
    JSONObject tokenObject = new JSONObject();
    try {
      tokenObject.put("token", token);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    try {
      final String url = Urls.VERIFY_URL;
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
      return restTemplate.postForObject(url, token, Boolean.class);
    } catch (Exception e) {
      Log.e("VerifyService", e.getMessage(), e);
    }

    return null;
  }

  @Override
  protected void onPostExecute(Boolean successs) {
    if (successs) {
      // Hey, we are registered
      // let's go to the main page
    } else {

    }
  }
}
