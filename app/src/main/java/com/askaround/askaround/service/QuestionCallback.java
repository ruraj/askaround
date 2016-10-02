package com.askaround.askaround.service;

import com.askaround.askaround.model.Question;

/**
 * Created by ruraj on 10/1/16.
 */
public interface QuestionCallback {
  void onQuestionsReceived(Question[] questions);
}
