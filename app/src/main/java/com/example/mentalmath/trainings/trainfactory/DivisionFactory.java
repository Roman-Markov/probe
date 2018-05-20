package com.example.mentalmath.trainings.trainfactory;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;
import com.example.mentalmath.trainings.IAnswerField;
import com.example.mentalmath.trainings.IExampleBuilder;
import com.example.mentalmath.trainings.IExampleDisplay;
import com.example.mentalmath.trainings.fields.DivisionAnswerField;
import com.example.mentalmath.trainings.fields.SimpleExampleDisplay;
import com.example.mentalmath.trainings.examplegenerator.DivisionBuilder;

/**
 * Created by Роман on 12.05.2018.
 */

public class DivisionFactory extends ATrainingPartsAbstractFactory {

    public static final String DIVIDEND_KEY;
    public static final String DIVISOR_KEY;
    public static final String AMOUNT_KEY;
    public static final String FORMAT_KEY;
    public static final String VISTIME_KEY;
    public static final String HONESTMODE_KEY;
    public static final String STOPWATCH_MODE_KEY;
    public static final String REMINDER_MODE_KEY;

    public static final String DEF_VAL_FOR_DIVIDEND;
    public static final String DEF_VAL_FOR_DIVISOR;
    public static final String DEF_VAL_FOR_AMOUNT;
    public static final String DEF_VAL_FOR_FORMAT;
    public static final String DEF_VAL_FOR_VISTIME;

    public static final boolean DEF_VAL_FOR_HONESTMODE;
    public static final boolean DEF_VAL_FOR_STOPWATCHMODE;
    public static final boolean DEF_VAL_FOR_REMINDER_MODE;

    private static final String ROW = "rowId";
    private static final String COLUMN = "columnId";


    static {
        DIVIDEND_KEY = Helper.mGlobalContext.getString(R.string.dividendRangeListKey);
        DIVISOR_KEY = Helper.mGlobalContext.getString(R.string.divisorRangeListKey);

        AMOUNT_KEY = Helper.mGlobalContext.getString(R.string.divisionQuantityKey);
        FORMAT_KEY = Helper.mGlobalContext.getString(R.string.divisionFormatListKey);
        VISTIME_KEY = Helper.mGlobalContext.getString(R.string.divisionVisTimeListKey);
        HONESTMODE_KEY = Helper.mGlobalContext.getString(R.string.divisionCbHonestModeKey);
        STOPWATCH_MODE_KEY = Helper.mGlobalContext.getString(R.string.divisionCbShowStopwatchKey);
        REMINDER_MODE_KEY = Helper.mGlobalContext.getString(R.string.divisionCbWithReminderKey);

        DEF_VAL_FOR_DIVIDEND = Helper.mGlobalContext.getString(R.string.id_dividend_1_9);
        DEF_VAL_FOR_DIVISOR = Helper.mGlobalContext.getString(R.string.id_divisor_1_9);

        DEF_VAL_FOR_AMOUNT = Helper.mGlobalContext.getString(R.string.id1);
        DEF_VAL_FOR_FORMAT = Helper.mGlobalContext.getString(R.string.formatAsRowId);
        DEF_VAL_FOR_VISTIME = Helper.mGlobalContext.getString(R.string.visTimeAlwaysId);
        DEF_VAL_FOR_HONESTMODE = false;
        DEF_VAL_FOR_STOPWATCHMODE = true;
        DEF_VAL_FOR_REMINDER_MODE = false;
    }

    public DivisionFactory(LayoutInflater inflater, ViewGroup container,
                           Activity activity) {
        super(inflater, container, activity);
    }

    @Override
    public IAnswerField getAnswerField() {
        return new DivisionAnswerField(mViewGroup, isHonestModeEnabled());
    }

    @Override
    public IExampleBuilder getExampleBuilder() {

        // TODO: 12.05.2018 replace with normal division builder
        String dividend = mPrefs.getString(DIVIDEND_KEY, "simple");
        String divisor = mPrefs.getString(DIVISOR_KEY, "simple");
        DivisionBuilder builder =(DivisionBuilder) DivisionBuilder.create(dividend, divisor);
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
