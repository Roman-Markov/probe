package com.example.mentalmath.trainings;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleExampleDisplay extends ABaseField implements IExampleDisplay {

    private static final String ALWAYS = "always";
    private static final String SEC30 = "30sec";
    private static final String SEC20 = "20sec";
    private static final String SEC15 = "15sec";
    private static final String SEC12 = "12sec";
    private static final String SEC10 = "10sec";
    private static final String SEC8 = "8sec";
    private static final String SEC6 = "6sec";
    private static final String SEC4 = "4sec";
    private static final String SEC3 = "3sec";
    private static final String SEC2 = "2sec";
    private static final String SEC1 = "1sec";


    private TextView mExampleView;
    private IExampleBuilder mExampleBuilder;
    private String mCurrentExample;
    private int mVisibilityTime = 0;
    private boolean mIsShouldShowExample = true;

    private Handler mHandler = new Handler();

    public SimpleExampleDisplay (LayoutInflater inflater, ViewGroup container, IExampleBuilder builder) {

        super(inflater, container, R.layout.example_display_field);
        mExampleView = mLayout.findViewById(R.id.example_view);
        mExampleBuilder = builder;
    }

    /**
     * Sets time of example visibility in seconds
     * @param prefsTime
     */
    public void setVisibilityTime(String prefsTime) {
        switch (prefsTime) {
            case ALWAYS:
                mVisibilityTime = 0;
                break;
            case SEC30:
                mVisibilityTime = 30;
                break;
            case SEC20:
                mVisibilityTime = 20;
                break;
            case SEC15:
                mVisibilityTime = 15;
                break;
            case SEC12:
                mVisibilityTime = 12;
                break;
            case SEC10:
                mVisibilityTime = 10;
                break;
            case SEC8:
                mVisibilityTime = 8;
                break;
            case SEC6:
                mVisibilityTime = 6;
                break;
            case SEC4:
                mVisibilityTime = 4;
                break;
            case SEC3:
                mVisibilityTime = 3;
                break;
            case SEC2:
                mVisibilityTime = 2;
                break;
            case SEC1:
                mVisibilityTime = 1;
                break;
        }
    }

    @Override
    public void showNewExample() {
        mCurrentExample = mExampleBuilder.generateExample();
        mExampleView.setText(mCurrentExample);
        if (mVisibilityTime != 0) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    synchronized (SimpleExampleDisplay.this) {
                        mIsShouldShowExample = false;
                        hideExample();
                    }
                }
            }, mVisibilityTime * 1000);
        }
    }

    @Override
    public void hideExample() {
        mExampleView.setText("");
    }

    @Override
    public synchronized void showExample() {
        if (mIsShouldShowExample) {
            mExampleView.setText(mCurrentExample);
        }
    }

    @Override
    public void showHiddenExample() {
        mExampleView.setText(mCurrentExample);
    }

    @Override
    public String getCurrentAnswer() {
        return mExampleBuilder.getCurrentAnswer();
    }

    public boolean checkResult(String userAnswer) {
        return mExampleBuilder.checkResult(userAnswer);
    }

}
