package com.example.mentalmath.trainings.trainfactory;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;
import com.example.mentalmath.trainings.IExampleBuilder;
import com.example.mentalmath.trainings.IExampleDisplay;
import com.example.mentalmath.trainings.examplegenerator.MultiplicationBuilder;
import com.example.mentalmath.trainings.fields.SimpleExampleDisplay;

/**
 * Created by Роман on 27.09.2017.
 */

public class MultiplicationFactory extends ATrainingPartsAbstractFactory {

    public static final String KIND_KEY;
    public static final String AMOUNT_KEY;
    public static final String FORMAT_KEY;
    public static final String VISTIME_KEY;
    public static final String HONESTMODE_KEY;
    public static final String STOPWATCH_MODE_KEY;

    public static final String DEF_VAL_FOR_KIND;
    public static final String DEF_VAL_FOR_AMOUNT;
    public static final String DEF_VAL_FOR_FORMAT;
    public static final String DEF_VAL_FOR_VISTIME;
    public static final boolean DEF_VAL_FOR_HONESTMODE;
    public static final boolean DEF_VAL_FOR_STOPWATCHMODE;

    private static final String ROW = "rowId";
    private static final String COLUMN = "columnId";


    static {
        KIND_KEY = Helper.mGlobalContext.getString(R.string.multiplicationKindListKey);
        AMOUNT_KEY = Helper.mGlobalContext.getString(R.string.multiplicationQuantityKey);
        FORMAT_KEY = Helper.mGlobalContext.getString(R.string.multiplicationFormatListKey);
        VISTIME_KEY = Helper.mGlobalContext.getString(R.string.multiplicationVisTimeListKey);
        HONESTMODE_KEY = Helper.mGlobalContext.getString(R.string.multiplicationCbHonestModeKey);
        STOPWATCH_MODE_KEY = Helper.mGlobalContext.getString(R.string.multiplicationCbShowStopwatchKey);

        DEF_VAL_FOR_KIND = Helper.mGlobalContext.getString(R.string.id_x5_5);
        DEF_VAL_FOR_AMOUNT = Helper.mGlobalContext.getString(R.string.id1);
        DEF_VAL_FOR_FORMAT = Helper.mGlobalContext.getString(R.string.formatAsRowId);
        DEF_VAL_FOR_VISTIME = Helper.mGlobalContext.getString(R.string.visTimeAlwaysId);
        DEF_VAL_FOR_HONESTMODE = false;
        DEF_VAL_FOR_STOPWATCHMODE = true;
    }

    public MultiplicationFactory(LayoutInflater inflater, ViewGroup container,
                              Activity activity) {
        super(inflater, container, activity);
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
    }

    @Override
    protected String getVisTimeKey() {
        return VISTIME_KEY;
    }

    @Override
    protected String getHonestModeKey() {
        return HONESTMODE_KEY;
    }

    @Override
    protected String getStopWatchModeKey() {
        return STOPWATCH_MODE_KEY;
    }

    @Override
    protected String getAmountKey() {
        return AMOUNT_KEY;
    }
}
