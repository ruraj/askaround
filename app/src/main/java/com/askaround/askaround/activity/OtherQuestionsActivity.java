package com.askaround.askaround.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import com.askaround.askaround.model.Question;
import com.askaround.askaround.service.QuestionCallback;
import com.askaround.askaround.service.QuestionService;

/**
 * Created by ruraj on 10/1/16.
 */
public class OtherQuestionsActivity extends Activity implements QuestionCallback {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    QuestionService questionGrabber = new QuestionService(this, this);
    questionGrabber.execute();
  }

  @Override
  public void onQuestionsReceived(Question[] questions) {

  }
}
