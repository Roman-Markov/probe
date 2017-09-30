package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.widget.LinearLayout;

/**
 * Created by Роман on 27.09.2017.
 */

public abstract class ATrainingPartsAbstractFactory implements ITrainingPartsFactory {

    LinearLayout mParentLayout;
    Fragment mFragment;

    IStopWatcher mStopWatch;

    public ATrainingPartsAbstractFactory(Fragment fragment,
                                         LinearLayout parentLayout,
                                         IStopWatcher stopWatch) {
        mFragment = fragment;
        mParentLayout = parentLayout;
        mStopWatch = stopWatch;
    }

    @Override
    public IStopWatchField getStopWatcherField(){return new SimpleStopWatchField(mFragment, mParentLayout, mStopWatch);}

    @Override
    public IExampleDisplay getExampleDisplay(){return new SimpleExampleDisplay(mParentLayout,
            getBuilder());}

    @Override
    public IAnswerField getAnswerField(){return new SimpleAnswerField(mParentLayout);}

    @Override
    public ISessionResultField getSessionResultField(){return new SimpleSessionResultField(mParentLayout);}

    @Override
    public abstract IExampleBuilder getExampleBuilder();

    @Override
    public boolean isHonestModeEnabled() {
        return false;
    }

    public void setStopWatcher(IStopWatcher sw) {
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
