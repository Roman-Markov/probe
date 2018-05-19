package com.example.mentalmath.trainings.fields;

import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.trainings.ABaseField;
import com.example.mentalmath.trainings.IStopWatch;
import com.example.mentalmath.trainings.IStopWatchField;

import java.nio.charset.Charset;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

    /**
     * Sometimes rounding value of time leads to sum of example time differ from total time.
     * In order to avoid this sum of example time will be accumulated here and than passed to
     * stopwatch (mCommonTrainStopWatch) and sets it's current time.
     */
    private long mAdjustmentTime;

    private boolean mIsFirstTrainSet = false;

    public SimpleStopWatchField (boolean isStopwatchVisible, LayoutInflater inflater, ViewGroup container, IStopWatch sw) {

        super(inflater, container, R.layout.stopwatch_field);
        init(sw, isStopwatchVisible);
    }

    public SimpleStopWatchField (boolean isStopwatchVisible, ViewGroup container, IStopWatch sw) {

        super(container, R.id.stopWatchField);
        init(sw, isStopwatchVisible);
    }

    private void init(IStopWatch stopWatch, boolean isStopwatchVisible) {
        mStopWatch = stopWatch;
        mCommonTrainStopWatch = mLayout.findViewById(R.id.swTotal);
        mCurrentTrainStopWatch = mLayout.findViewById(R.id.stopwatch);
        // TODO: 14.04.2018 replace with more smart logic which doesn't shows stopwatch field itself
        if (!isStopwatchVisible) {
            mCommonTrainStopWatch.setTextColor(mCommonTrainStopWatch.getDrawingCacheBackgroundColor());
            mCurrentTrainStopWatch.setTextColor(mCurrentTrainStopWatch.getDrawingCacheBackgroundColor());
        }
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
    public void resetFields(ViewGroup layout) {
        mLayout = layout;
        int visibility = mCommonTrainStopWatch.getVisibility();
        mCommonTrainStopWatch = mLayout.findViewById(R.id.swTotal);
        mCommonTrainStopWatch.setVisibility(visibility);
        visibility = mCurrentTrainStopWatch.getVisibility();
        mCurrentTrainStopWatch = mLayout.findViewById(R.id.stopwatch);
        mCurrentTrainStopWatch.setVisibility(visibility);
    }

    @Override
    public void start() {
        mIsFirstTrainSet = true;
        mCurrentTrainStartTime = 0;
        mAdjustmentTime = 0;
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
        /* Sometimes rounding value of time leads to sum of example time slightly differ from total time.
         * In order to avoid this sum of example time will be accumulated here and than passed to
         * stopwatch and set it's current time.
         */
        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss:SS");
        timeFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
        Date currentTiime = timeFormat.parse(mCurrentTrainStopWatch.getText().toString(), new ParsePosition(0));
        long current = currentTiime.getTime();
        mAdjustmentTime += current;

        pause();

        mStopWatch.setCurrentTime(mAdjustmentTime);
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
