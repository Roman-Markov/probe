package com.example.mentalmath.trainings;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleExampleDisplay extends ABaseField implements IExampleDisplay {

    TextView mExampleView;
    IExampleBuilder mExampleBuilder;
    String mCurrentExample;

    public SimpleExampleDisplay(LinearLayout parentLayout, IExampleBuilder builder) {

        super((LinearLayout) parentLayout.findViewById(R.layout.example_display_field));
        mExampleView = mLayout.findViewById(R.id.example_view);
        mExampleBuilder = builder;
    }

    @Override
    public void showNewExample() {
        mCurrentExample = mExampleBuilder.generateExample();
        mExampleView.setText(mCurrentExample);
    }

    @Override
    public void hideExample() {
        mExampleView.setText("");
    }

    @Override
    public void showExample() {
        mExampleView.setText(mCurrentExample);
    }

    @Override
    public String getCurrentAnswer() {
        return mExampleBuilder.getCurrentAnswer();
    }

    @Override
    public IExampleBuilder getExampleBuilder() {
        return mExampleBuilder;
    }
}
