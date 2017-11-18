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

public class MultiplicationFactory extends ATrainingPartsAbstractFactory {

    public static final String KIND_KEY;
    public static final String AMOUNT_KEY;
    public static final String FORMAT_KEY;
    public static final String VISTIME_KEY;
    public static final String HONESTMODE_KEY;

    public static final String DEF_VAL_FOR_KIND;
    public static final String DEF_VAL_FOR_AMOUNT;
    public static final String DEF_VAL_FOR_FORMAT;
    public static final String DEF_VAL_FOR_VISTIME;
    public static final String DEF_VAL_FOR_HONESTMODE;

    private static final String ROW = "rowId";
    private static final String COLUMN = "columnId";


    static {
        KIND_KEY = Helper.mGlobalContext.getString(R.string.multiplicationKindListKey);
        AMOUNT_KEY = Helper.mGlobalContext.getString(R.string.multiplicationQuantityKey);
        FORMAT_KEY = Helper.mGlobalContext.getString(R.string.multiplicationFormatListKey);
        VISTIME_KEY = Helper.mGlobalContext.getString(R.string.multiplicationVisTimeListKey);
        HONESTMODE_KEY = Helper.mGlobalContext.getString(R.string.multiplicationCbHonestModeKey);

        DEF_VAL_FOR_KIND = Helper.mGlobalContext.getString(R.string.id_x5_5);;
        DEF_VAL_FOR_AMOUNT = Helper.mGlobalContext.getString(R.string.id1);;
        DEF_VAL_FOR_FORMAT = Helper.mGlobalContext.getString(R.string.formatAsRowId);;
        DEF_VAL_FOR_VISTIME = Helper.mGlobalContext.getString(R.string.visTimeAlwaysId);;
        DEF_VAL_FOR_HONESTMODE = Helper.mGlobalContext.getString(R.string.no);;
    }

    public MultiplicationFactory(LayoutInflater inflater, ViewGroup container,
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
    public IExampleBuilder getExampleBuilder() {

        String kind = mPrefs.getString(KIND_KEY, "simple");
        MultiplicationBuilder builder =(MultiplicationBuilder) MultiplicationBuilder.create(kind);
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
