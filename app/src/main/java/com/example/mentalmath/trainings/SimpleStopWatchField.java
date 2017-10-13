package com.example.mentalmath.trainings;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mentalmath.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleStopWatchField extends ABaseField implements IStopWatchField {

    private IStopWatch mStopWatch;

    private TextView mCommonTrainStopWatch;
    private TextView mCurrentTrainStopWatch;

    private Handler mHandler = new Handler();
    private Runnable mUiUpdate;

    private SimpleDateFormat mTimeFormat = new SimpleDateFormat("mm:ss:SS");
    private Date mDate = new Date();

    private long mCurrentTrainStartTime;

    private boolean mIsFirstTrainSet = false;

    public SimpleStopWatchField (LayoutInflater inflater, ViewGroup container, IStopWatch sw) {

        super(inflater, container, R.layout.stopwatch_field);;
        mStopWatch = sw;
        mCommonTrainStopWatch = mLayout.findViewById(R.id.swTotal);
        mCurrentTrainStopWatch = mLayout.findViewById(R.id.stopwatch);
        resetStopWatch(mCommonTrainStopWatch, mCurrentTrainStopWatch);

        mUiUpdate = new Runnable() {
            @Override
            public void run() {
                updateUI();
                mHandler.postDelayed(this, 10);
            }
        };
    }

    @Override
    public void start() {
        mIsFirstTrainSet = true;
        mCurrentTrainStartTime = 0;
        mStopWatch.start();
        resetStopWatch(mCommonTrainStopWatch, mCurrentTrainStopWatch);
        mHandler.removeCallbacks(mUiUpdate);
        mHandler.post(mUiUpdate);
    }

    @Override
    public void startExample() {

        // to eliminate small difference between stopwatches in the first run
        if (mIsFirstTrainSet) {
            mCurrentTrainStartTime = 0;
            mIsFirstTrainSet = false;
        } else {
            mCurrentTrainStartTime = mStopWatch.getCurrentTime();
        }
        resetStopWatch(mCurrentTrainStopWatch);
        resume();
    }

    @Override
    public void resume() {
        mStopWatch.resume();
        mHandler.post(mUiUpdate);
    }

    @Override
    public void pause() {
        mStopWatch.pause();
        mHandler.removeCallbacks(mUiUpdate);
    }

    @Override
    public void stopExample() {
        pause();
    }

    @Override
    public void stopAll() {
        resetAll();
    }

    @Override
    public String getCurrentExampleTime() {
        return mCurrentTrainStopWatch.getText().toString();
    }

    @Override
    public String getTotalTime() {
        return mCommonTrainStopWatch.getText().toString();
    }

    private void resetAll() {
        mHandler.removeCallbacks(mUiUpdate);
        mStopWatch.stop();
        mCurrentTrainStartTime = 0;
        resetStopWatch(mCommonTrainStopWatch, mCurrentTrainStopWatch);
    }

    private void resetStopWatch(TextView... textViews) {
        for(TextView tv: textViews) {
            tv.setText(R.string.initTime);
        }
    }

    private void updateUI() {

        long totalTimePassed = mStopWatch.getCurrentTime();
        mDate.setTime(totalTimePassed);
        String total = mTimeFormat.format(mDate);

        long currentTrainTimePassed = totalTimePassed - mCurrentTrainStartTime;
        mDate.setTime(currentTrainTimePassed);
        String result = mTimeFormat.format(mDate);

        mCommonTrainStopWatch.setText(total);
        mCurrentTrainStopWatch.setText(result);
    }
}
