package com.askaround.askaround.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.askaround.askaround.model.Question;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by ruraj on 10/1/16.
 */
public class QuestionService extends AsyncTask<String, Void, Question[]> {

  private Context context;
  private QuestionCallback callback;

  public QuestionService(Context context, QuestionCallback callback) {
    this.context = context;
    this.callback = callback;
  }

  @Override
  protected Question[] doInBackground(String... strings) {
    String scope = strings[0];

    try {
      final String url = scope.equals("my") ? Urls.MY_QUESTIONS_URL : Urls.OTHERS_QUESTIONS_URL;
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
      return restTemplate.getForObject(url, Question[].class);
    } catch (Exception e) {
      Log.e("RegisterService", e.getMessage(), e);
    }

    return null;
  }

  @Override
  protected void onPostExecute(Question[] questions) {
    callback.onQuestionsReceived(questions);
  }
}
