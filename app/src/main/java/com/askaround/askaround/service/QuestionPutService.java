package com.askaround.askaround.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.askaround.askaround.model.Question;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ruraj on 10/1/16.
 */
public class QuestionPutService extends AsyncTask<Question, Void, Void> {

  private Context context;
  private QuestionCallback callback;

  public QuestionPutService(Context context, QuestionCallback callback) {
    this.context = context;
    this.callback = callback;
  }

  @Override
  protected Void doInBackground(Question ... questions) {
    try {
      final String url = Urls.POST_QUESTION_URL;
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
      restTemplate.postForObject(url, questions, Void.class);
    } catch (Exception e) {
      Log.e("RegisterService", e.getMessage(), e);
    }

    return null;
  }

  @Override
  protected void onPostExecute(Void voider) {
  }
}
