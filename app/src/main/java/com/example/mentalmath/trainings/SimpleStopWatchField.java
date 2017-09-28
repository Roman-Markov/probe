package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleStopWatchField implements IStopWatchField {

    Fragment mFragment;
    private IStopWatcher mStopWatcher;
    private LinearLayout mLayout;

    TextView mAllTrainStopWatch;
    TextView mTrainExerciseWatch;

    private Handler m_handler = new Handler();
    private Runnable m_uiUpdate;

    private long exampleTimePassed;

    public SimpleStopWatchField(Fragment fragment,LinearLayout layout, IStopWatcher sw) {
        mFragment = fragment;
        mStopWatcher = sw;
        mLayout = layout.findViewById(R.layout.stopwatch_field);
        mAllTrainStopWatch = mLayout.findViewById(R.id.swTotal);
        mTrainExerciseWatch = mLayout.findViewById(R.id.stopwatch);
        resetStopWatch(mAllTrainStopWatch, mTrainExerciseWatch);

        layout.addView(mLayout);

        m_uiUpdate = new Runnable() {
            @Override
            public void run() {
                updateUI();
                m_handler.postDelayed(this, 10);
            }
        };
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
        long timeForExersice = totalTimePassed - exampleTimePassed;
        formattedTime = formatLongMillis(timeForExersice);
        String result = String.format(mFragment.getString(R.string.stopwatchFormat), formattedTime[0], formattedTime[1]);
        mAllTrainStopWatch.setText(total);
        mTrainExerciseWatch.setText(result);
    }

    /**
     * format long mils to itn[2]{sec, santisecs};
     * @param mils - passed time in milliseconds
     * @return itn[2]{sec, santisecs}
     */
    private int[] formatLongMillis(long mils) {
        int[] time = new int[2];
        time[0] = (int) (mils/1000);
        time[1] = (int) ((int) (mils%1000)/10);
        return time;
    }

    @Override
    public void start() {
        exampleTimePassed = 0;
        mStopWatcher.start();
        resetStopWatch(mAllTrainStopWatch, mTrainExerciseWatch);
        m_handler.removeCallbacks(m_uiUpdate);
        m_handler.post(m_uiUpdate);
    }

    @Override
    public void startExample() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stopExample() {
        exampleTimePassed = mStopWatcher.getCurrentTime();
    }

    @Override
    public void stopAll() {
        exampleTimePassed = 0;
    }

    private void init() {

    }
}
