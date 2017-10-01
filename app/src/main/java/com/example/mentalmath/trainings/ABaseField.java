package com.example.mentalmath.trainings;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Роман on 30.09.2017.
 */

public abstract class ABaseField implements IField{

    protected LinearLayout mLayout;

    public ABaseField(LayoutInflater inflater, ViewGroup container, int rID) {
        mLayout = (LinearLayout) inflater.inflate(rID, container, false);
    }

    @Override
    public LinearLayout getLayout() {
        return mLayout;
    }
}
