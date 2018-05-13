package com.example.mentalmath.trainings.trainfactory;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mentalmath.trainings.IAnswerField;
import com.example.mentalmath.trainings.IExampleBuilder;
import com.example.mentalmath.trainings.IExampleDisplay;
import com.example.mentalmath.trainings.ISessionResultField;
import com.example.mentalmath.trainings.IStopWatchField;
import com.example.mentalmath.trainings.ITrainingPartsFactory;
import com.example.mentalmath.trainings.fields.SimpleAnswerField;
import com.example.mentalmath.trainings.SimpleExampleBuilder;
import com.example.mentalmath.trainings.fields.SimpleExampleDisplay;
import com.example.mentalmath.trainings.fields.SimpleSessionResultField;
import com.example.mentalmath.trainings.SimpleStopWatch;
import com.example.mentalmath.trainings.fields.SimpleStopWatchField;

/**
 * Created by Роман on 27.09.2017.
 */

public abstract class ATrainingPartsAbstractFactory implements ITrainingPartsFactory {

    protected LayoutInflater mInflater;
    protected ViewGroup mViewGroup;
    protected SharedPreferences mPrefs;

    public ATrainingPartsAbstractFactory(LayoutInflater inflater, ViewGroup container,
                                         Activity activity) {
        mInflater = inflater;
        mViewGroup = container;
        mPrefs = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    @Override
    public IStopWatchField getStopWatcherField(){return new SimpleStopWatchField(isStopwatchVisible(), mInflater, mViewGroup, new SimpleStopWatch());}

    @Override
    public IExampleDisplay getExampleDisplay(){return new SimpleExampleDisplay(mInflater, mViewGroup,
            getExampleBuilder());}

    @Override
    public IAnswerField getAnswerField(){return new SimpleAnswerField(mInflater, mViewGroup, isHonestModeEnabled());}

    @Override
    public ISessionResultField getSessionResultField(){return new SimpleSessionResultField(mInflater, mViewGroup);}


    @Override
    public IExampleBuilder getExampleBuilder() {
        return new SimpleExampleBuilder();
    };

    @Override
    public boolean isHonestModeEnabled() {
        return false;
    }

    @Override
    public boolean isStopwatchVisible() {
        return true;
    }

    @Override
    public int getAmountOfExamples(){
        //todo override in subclasses
        return 2;
    };

}
