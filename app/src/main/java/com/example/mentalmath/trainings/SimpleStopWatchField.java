package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleStopWatchField extends ABaseField implements IStopWatchField {

    Fragment mFragment;
    private IStopWatch mStopWatcher;

    TextView mCommonTrainStopWatch;
    TextView mCurrentTrainStopWatch;

    private Handler mHandler = new Handler();
    private Runnable mUiUpdate;

    private long mCurrentTrainStartTime;

    private boolean mIsFirstTrainSet = false;

    public SimpleStopWatchField (Fragment fragment, LayoutInflater inflater, ViewGroup container, IStopWatch sw) {

        super(inflater, container, R.layout.stopwatch_field);;
        mFragment = fragment;
        mStopWatcher = sw;
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
        mStopWatcher.start();
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
            mCurrentTrainStartTime = mStopWatcher.getCurrentTime();
        }
    }

    @Override
    public void resume() {
        mStopWatcher.resume();
        mHandler.post(mUiUpdate);
    }

    @Override
    public void pause() {
        mStopWatcher.pause();
        mHandler.removeCallbacks(mUiUpdate);
    }

    @Override
    public void stopExample() {
        resetStopWatch(mCurrentTrainStopWatch);
    }

    @Override
    public void stopAll() {
        resetAll();
    }


    private void init() {

    }

    private void resetAll() {
        mStopWatcher.stop();
        mCurrentTrainStartTime = 0;
        resetStopWatch(mCommonTrainStopWatch, mCurrentTrainStopWatch);
    }

    private void resetStopWatch(TextView... textViews) {
        for(TextView tv: textViews) {
            tv.setText(R.string.initTime);
        }
    }

    private void updateUI() {

        long totalTimePassed = mStopWatcher.getCurrentTime();
        int[] formattedTime = formatLongMillis(totalTimePassed);
        String total = String.format(mFragment.getString(R.string.stopwatchFormat), formattedTime[0], formattedTime[1]);

        long currentTrainTimePassed = totalTimePassed - mCurrentTrainStartTime;
        formattedTime = formatLongMillis(currentTrainTimePassed);
        String result = String.format(mFragment.getString(R.string.stopwatchFormat), formattedTime[0], formattedTime[1]);

        mCommonTrainStopWatch.setText(total);
        mCurrentTrainStopWatch.setText(result);
    }

    /**
     * Formats long mils to itn[2]{sec, santisecs};
     * @param mils - passed time in milliseconds
     * @return itn[2]{sec, santisecs}
     */
    private int[] formatLongMillis(long mils) {
        int[] time = new int[2];
        time[0] = (int) (mils/1000);
        time[1] = (int) ((int) (mils%1000)/10);
        return time;
    }
}