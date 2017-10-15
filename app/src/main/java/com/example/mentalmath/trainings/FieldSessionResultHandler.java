package com.example.mentalmath.trainings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;

/**
 * Created by Роман on 14.10.2017.
 */

public class FieldSessionResultHandler extends ABaseField implements ISessionResultField {


    int mExampleCounter;
    View mDivider;

    public FieldSessionResultHandler(LayoutInflater inflater, ViewGroup container) {

        super(inflater, container, R.layout.session_result_layout);
        mDivider = inflater.inflate(R.layout.divider, container, false);
    }

    @Override
    public void addExampleResult(String time, boolean isRight) {
        mExampleCounter++;
        String result;
        if (isRight) {
            result = mLayout.getContext().getString(R.string.right);
        } else {
            result = mLayout.getContext().getString(R.string.wrong);
        }

        TextView view = new TextView(mLayout.getContext());
        view.setText(String.format(mLayout.getContext().getString(R.string.exampleResultFormat),
                mExampleCounter, time, result.toLowerCase()));

        Helper.shiftChildren(mLayout, view, 0);

        View v = new View(mLayout.getContext());

        Helper.shiftChildren(mLayout, mDivider, 0);
//        mLayout.addView(v);
    }

    @Override
    public void addCommonResult(String commonResult) {
        mExampleCounter = 0;

        TextView view = new TextView(mLayout.getContext());
        view.setText(commonResult);
        Helper.shiftChildren(mLayout, view, 0);
    }

    @Override
    public void reset() {
        mLayout.removeAllViews();
    }
}
