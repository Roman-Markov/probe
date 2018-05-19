package com.example.mentalmath.trainings.fields;

import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.trainings.ABaseField;
import com.example.mentalmath.trainings.IExampleBuilder;
import com.example.mentalmath.trainings.IExampleDisplay;

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
    private boolean mIsShouldHideExample = true;

    private Handler mHandler = new Handler();
    private Runnable mGuiUpdater;

    public SimpleExampleDisplay (LayoutInflater inflater, ViewGroup container, IExampleBuilder builder) {

        super(inflater, container, R.layout.example_display_field);
        init(builder);
    }

    public SimpleExampleDisplay (ViewGroup container, IExampleBuilder builder) {

        super(container, R.id.exampleDisplayField);
        init(builder);
    }

    private void init(IExampleBuilder builder) {
        mExampleView = mLayout.findViewById(R.id.example_view);
        mExampleBuilder = builder;
    }

    @Override
    public void resetFields(ViewGroup layout) {
        mLayout = layout;
        int visibility = mExampleView.getVisibility();
        CharSequence text = mExampleView.getText().toString();
        mExampleView = mLayout.findViewById(R.id.example_view);
        mExampleView.setVisibility(visibility);
        mExampleView.setText(text);
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
        mIsShouldHideExample = true;
        mCurrentExample = mExampleBuilder.generateExample();
        mExampleView.setText(mCurrentExample);
        if (mVisibilityTime != 0) {
            // remove previous gui updaters to avoid hiding example in unexpected time
            if (mGuiUpdater != null) {
                mHandler.removeCallbacks(mGuiUpdater);
            }
            mGuiUpdater = new Runnable() {
                @Override
                public void run() {
                    synchronized (SimpleExampleDisplay.this) {
                        if (mIsShouldHideExample) {
                            mIsShouldShowExample = false;
                            hideExample();
                        }
                    }
                }
            };
            mHandler.postDelayed(mGuiUpdater, mVisibilityTime * 1000);
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
    public synchronized void showHiddenExample() {
        mExampleView.setText(mCurrentExample);
        mIsShouldHideExample = false;
    }

    @Override
    public String getCurrentRightAnswer() {
        return mExampleBuilder.getCurrentAnswer();
    }

    public boolean checkResult(String userAnswer) {
        return mExampleBuilder.checkResult(userAnswer);
    }

    /**
     * should be called in {@link Fragment#onStop()}
     */
    public void onStrop() {
        mHandler.removeCallbacks(mGuiUpdater);
    }

    /**
     * should be called in {@link Fragment#onStart()}
     */
    public void onStart() {
        // common time of visibility will be in this case more than set by user
        // but more likely that user don't expect thing which lead to calling onStop() and onStart()
        mHandler.postDelayed(mGuiUpdater, mVisibilityTime * 1000);
    }

}
