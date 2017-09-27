package com.example.mentalmath.trainings;

import android.widget.LinearLayout;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleStopWatcherField implements IStopWatcherField {

    private IStopWatcher mStopWatcher;
    private LinearLayout mLayout;

    public SimpleStopWatcherField(LinearLayout layout, IStopWatcher sw) {
        mStopWatcher = sw;
        mLayout = layout;
    }

    @Override
    public void start() {

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

    }

    @Override
    public void stopAll() {

    }
}
