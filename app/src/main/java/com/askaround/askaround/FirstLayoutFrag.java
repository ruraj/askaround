package com.askaround.askaround;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jearbear on 10/1/2016.
 */
public class FirstLayoutFrag extends Fragment {
    View newView;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newView = inflater.inflate(R.layout.first_layout,container,false);
        return newView;
    }
}
