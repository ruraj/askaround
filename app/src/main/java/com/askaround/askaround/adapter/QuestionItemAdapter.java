package com.askaround.askaround.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.askaround.askaround.R;
import com.askaround.askaround.model.Question;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ruraj on 10/2/16.
 */
public class QuestionItemAdapter extends ArrayAdapter<Question> {
  private final SimpleDateFormat ITEM_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  public QuestionItemAdapter(Context context, int resource, List<Question> objects) {
    super(context, resource, objects);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.questionitem, parent, false);
    }

    TextView title = (TextView) convertView.findViewById(R.id.title);
    TextView description = (TextView) convertView.findViewById(R.id.description);
    TextView date = (TextView) convertView.findViewById(R.id.date);
    TextView category = (TextView) convertView.findViewById(R.id.category);
    TextView responses = (TextView) convertView.findViewById(R.id.responses);
    TextView location = (TextView) convertView.findViewById(R.id.location);

    Question question = getItem(position);

    title.setText(question.getTitle());
    description.setText(question.getDescription());
    date.setText(ITEM_DATE_FORMAT.format(question.getDatePublished()));
    category.setText(question.getCategory());
    responses.setText(String.valueOf(question.getResponseCount()));
    location.setText(question.getFormattedAddress());

    return convertView;
  }
}
