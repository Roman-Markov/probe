package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by Роман on 27.09.2017.
 */

public abstract class ATrainingPartsAbstractFactory implements ITrainingPartsFactory {

    LayoutInflater mInflater;
    ViewGroup mViewGroup;
    IStopWatch mStopWatch;

    public ATrainingPartsAbstractFactory(LayoutInflater inflater, ViewGroup container,
                                         IStopWatch stopWatch) {
        mInflater = inflater;
        mViewGroup = container;
        mStopWatch = stopWatch;
    }

    @Override
    public IStopWatchField getStopWatcherField(){return new SimpleStopWatchField(mInflater, mViewGroup, mStopWatch);}

    @Override
    public IExampleDisplay getExampleDisplay(){return new SimpleExampleDisplay(mInflater, mViewGroup,
            getBuilder());}

    @Override
    public IAnswerField getAnswerField(){return new SimpleAnswerField(mInflater, mViewGroup, isHonestModeEnabled());}

    @Override
    public ISessionResultField getSessionResultField(){return new SimpleSessionResultField(mInflater, mViewGroup);}

    @Override
    public abstract IExampleBuilder getExampleBuilder();

    @Override
    public boolean isHonestModeEnabled() {
        return false;
    }

    public void setStopWatcher(IStopWatch sw) {
        mStopWatch = sw;
    }

    public int getAmountOfExamles(){
        //todo override in subclasses
        return 2;
    };

    protected IExampleBuilder getBuilder() {
        //todo add logic or move it to subclass
        return new SimpleExampleBuilder();
    }
}
