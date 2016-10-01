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
public class QuestionService extends AsyncTask<String, Void, Boolean> {

  @Override
  protected Boolean doInBackground(String... strings) {
    String scope = strings[0];

    try {
      final String url = scope.equals("my") ? Urls.MY_QUESTIONS_URL : Urls.OTHERS_QUESTIONS_URL;
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
      return restTemplate.getForObject(url, Boolean.class);
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
