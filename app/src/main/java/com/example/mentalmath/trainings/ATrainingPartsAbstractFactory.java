package com.example.mentalmath.trainings;

import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by Роман on 27.09.2017.
 */

public abstract class ATrainingPartsAbstractFactory implements ITrainingPartsFactory {

    LinearLayout mStopWatcherLayout;
    LinearLayout mExampleDisplayLayout;
    LinearLayout mAnswerFieldLayout;
    LinearLayout mSessionResultLayout;

    IStopWatcher mStopWatcher;

    public ATrainingPartsAbstractFactory(LinearLayout layoutForStopWatcher,
                                         LinearLayout layoutForExampleDisplay,
                                         LinearLayout layoutForAnswerField,
                                         LinearLayout layoutForSessionResultField) {
        mStopWatcherLayout = layoutForStopWatcher;
        mExampleDisplayLayout = layoutForExampleDisplay;
        mAnswerFieldLayout = layoutForAnswerField;
        mSessionResultLayout = layoutForSessionResultField;

        mStopWatcher = new SimpleStopWatcher();
    }

    @Override
    public IStopWatcherField getStopWatcherField(){return new SimpleStopWatcherField(mStopWatcherLayout, mStopWatcher);}

    @Override
    public IExampleDisplay getExampleDisplay(){return new SimpleExampleDisplay(mExampleDisplayLayout);}

    @Override
    public IAnswerField getAnswerField(){return new SimpleAnswerField(mAnswerFieldLayout);}

    @Override
    public ISessionResultField getSessionResultField(){return new SimpleSessionResultField(mSessionResultLayout);}

    @Override
    public abstract IExampleBuilder getExampleBuilder();

    public void setStopWatcher(IStopWatcher sw) {
        mStopWatcher = sw;
    }
}
