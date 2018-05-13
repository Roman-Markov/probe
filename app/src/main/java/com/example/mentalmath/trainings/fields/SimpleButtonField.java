package com.example.mentalmath.trainings.fields;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.trainings.ABaseField;
import com.example.mentalmath.trainings.IControlButtonField;

/**
 * Created by Роман on 30.09.2017.
 */

public class SimpleButtonField extends ABaseField implements IControlButtonField {

    private Button mStartButton;
    private Button mOkButton;
    private Button mPauseButton;
    private Button mRightButton;
    private Button mWrongButton;


    public SimpleButtonField (LayoutInflater inflater, ViewGroup container) {

        super(inflater, container, R.layout.button_field);

        mStartButton    = mLayout.findViewById(R.id.startButton);
        mOkButton       = mLayout.findViewById(R.id.okButton);
        mPauseButton    = mLayout.findViewById(R.id.pauseButton);
        mRightButton    = mLayout.findViewById(R.id.rightButton);
        mWrongButton    = mLayout.findViewById(R.id.wrongButton);

    }

    @Override
    public Button getStartButton() {
        return mStartButton;
    }

    @Override
    public Button getOkButton() {
        return mOkButton;
    }

    @Override
    public Button getPauseButton() {
        return mPauseButton;
    }

    @Override
    public Button getRightButton() {
        return mRightButton;
    }

    @Override
    public Button getWrongButton() {
        return mWrongButton;
    }

    public TextView getCounterView() {
        return mLayout.findViewById(R.id.counter_view);
    }

}
