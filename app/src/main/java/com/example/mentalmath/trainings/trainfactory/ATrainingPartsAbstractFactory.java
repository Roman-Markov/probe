package com.example.mentalmath.trainings.trainfactory;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mentalmath.trainings.IAnswerField;
import com.example.mentalmath.trainings.IControlButtonField;
import com.example.mentalmath.trainings.IExampleBuilder;
import com.example.mentalmath.trainings.IExampleDisplay;
import com.example.mentalmath.trainings.ISessionResultField;
import com.example.mentalmath.trainings.IStopWatchField;
import com.example.mentalmath.trainings.ITrainingPartsFactory;
import com.example.mentalmath.trainings.fields.SimpleAnswerField;
import com.example.mentalmath.trainings.SimpleExampleBuilder;
import com.example.mentalmath.trainings.fields.SimpleButtonField;
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
    public IStopWatchField getStopWatcherField(){return new SimpleStopWatchField(isStopwatchVisible(), mViewGroup, new SimpleStopWatch());}

    @Override
    public IExampleDisplay getExampleDisplay(){
        IExampleDisplay display = new SimpleExampleDisplay(mViewGroup, getExampleBuilder());
        String visTime = mPrefs.getString(getVisTimeKey(), "always");
        display.setVisibilityTime(visTime);
        return display;
    }

    @Override
    public IControlButtonField getButtonField() {
        return new SimpleButtonField(mViewGroup);
    }

    @Override
    public IAnswerField getAnswerField(){return new SimpleAnswerField(mViewGroup, isHonestModeEnabled());}

    @Override
    public ISessionResultField getSessionResultField(){return new SimpleSessionResultField(mViewGroup);}


    @Override
    public IExampleBuilder getExampleBuilder() {
        return new SimpleExampleBuilder();
    };

    @Override
    public boolean isHonestModeEnabled() {
        return mPrefs.getBoolean(getHonestModeKey(), false);
    }

    @Override
    public boolean isStopwatchVisible() {
        return mPrefs.getBoolean(getStopWatchModeKey(), true);
    }

    @Override
    public int getAmountOfExamples(){
        String prefAmount = mPrefs.getString(getAmountKey(), "1");
        try {
            int amount = Integer.parseInt(prefAmount);
            return amount;
        } catch (NumberFormatException e) {
            Log.e(getClass().getSimpleName(), "Wrong format of number: " + prefAmount);
            return 1;
        }
    }

    protected abstract String getVisTimeKey();

    protected abstract String getHonestModeKey();

    protected abstract String getStopWatchModeKey();

    protected abstract String getAmountKey();

}
