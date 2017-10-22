package com.example.mentalmath.trainings;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;

/**
 * Created by Роман on 27.09.2017.
 */

public class ArithmeticTrainingPartsFactory extends ATrainingPartsAbstractFactory {

    private static final String KIND_KEY;
    private static final String AMOUNT_KEY;
    private static final String FORMAT_KEY;
    private static final String VISTIME_KEY;
    private static final String HONESTMODE_KEY;

    private static final String ROW = "row";
    private static final String COLUMN = "column";


    static {
        KIND_KEY = Helper.mGlobalContext.getString(R.string.additionKindListKey);
        AMOUNT_KEY = Helper.mGlobalContext.getString(R.string.additionQuantityKey);
        FORMAT_KEY = Helper.mGlobalContext.getString(R.string.additionFormatListKey);
        VISTIME_KEY = Helper.mGlobalContext.getString(R.string.additionVisTimeListKey);
        HONESTMODE_KEY = Helper.mGlobalContext.getString(R.string.additionCbHonestModeKey);
    }

    public ArithmeticTrainingPartsFactory(LayoutInflater inflater, ViewGroup container,
                                          Activity activity) {
        super(inflater, container, activity);
    }

    @Override
    public IExampleDisplay getExampleDisplay(){
        SimpleExampleDisplay display = new SimpleExampleDisplay(mInflater, mViewGroup,
            getExampleBuilder());
        String visTime = mPrefs.getString(VISTIME_KEY, "always");
        display.setVisibilityTime(visTime);
        return display;
    }

    @Override
    public IAnswerField getAnswerField(){return new SimpleAnswerField(mInflater, mViewGroup, isHonestModeEnabled());}

    @Override
    public ISessionResultField getSessionResultField(){return new SimpleSessionResultField(mInflater, mViewGroup);}

    @Override
    public IExampleBuilder getExampleBuilder() {

        String kind = mPrefs.getString(KIND_KEY, "simple");
        AdditionBuilder builder =(AdditionBuilder) AdditionBuilder.getAdditionBuilder(kind);
        String format = mPrefs.getString(FORMAT_KEY, "row");
        int formatType = IExampleBuilder.ROW_FORMAT;
        switch (format) {
            case ROW:
                formatType = IExampleBuilder.ROW_FORMAT;
                break;
            case COLUMN:
                formatType = IExampleBuilder.COLUMN_FORMAT;
                break;
        }
        builder.setFormat(formatType);
        return builder;
    };

    @Override
    public boolean isHonestModeEnabled() {
        return mPrefs.getBoolean(HONESTMODE_KEY, false);
    }

    @Override
    public int getAmountOfExamples(){
        String prefAmount = mPrefs.getString(AMOUNT_KEY, "1");
        try {
            int amount = Integer.parseInt(prefAmount);
            return amount;
        } catch (NumberFormatException e) {
            Log.e(getClass().getSimpleName(), "Wrong format of number: " + prefAmount);
            return 1;
        }
    }
}
