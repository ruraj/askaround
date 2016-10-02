package com.askaround.askaround.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.askaround.askaround.R;
import com.askaround.askaround.adapter.QuestionItemAdapter;
import com.askaround.askaround.model.Question;
import com.askaround.askaround.service.QuestionCallback;
import com.askaround.askaround.service.QuestionGetService;

import java.util.ArrayList;

/**
 * Created by ruraj on 10/1/16.
 */
public class OtherQuestionsActivity extends Activity implements QuestionCallback {

  private QuestionItemAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.content_main);

    ListView questionsListView = (ListView) findViewById(R.id.listOfQuestions);

    adapter = new QuestionItemAdapter(this, R.layout.questionitem, new ArrayList<Question>());

    QuestionGetService questionGrabber = new QuestionGetService(this, this);
    questionGrabber.execute("others");
  }

  @Override
  public void onQuestionsReceived(Question[] questions) {
    adapter.addAll(questions);
  }
}
