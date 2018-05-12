package com.example.mentalmath.trainchoice;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;
import com.example.mentalmath.trainings.MultiplicationFactory;
import com.example.mentalmath.trainings.MultiplicationFactory;
import com.example.mentalmath.trainings.MultiplicationFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Роман on 13.11.2017.
 */

public class MultiplicationDescriptionInflater extends ADescriptionInflater {

    public MultiplicationDescriptionInflater(int kind) {
        super(kind);
    }

    //==============================================================================================

    @Override
    protected int getKindArray() {
        return R.array.multiplicationTrainString;
    }

    @Override
    protected int getKindArrayId() {
        return R.array.multiplicationTrainStringId;
    }

    //==============================================================================================

    @Override
    protected String getDefValForKind() {
        return MultiplicationFactory.DEF_VAL_FOR_KIND;
    }

    @Override
    protected String getDefValForAmount() {
        return MultiplicationFactory.DEF_VAL_FOR_AMOUNT;
    }

    @Override
    protected String getDefValForVisTime() {
        return MultiplicationFactory.DEF_VAL_FOR_VISTIME;
    }

    @Override
    protected String getDefValForFormat() {
        return MultiplicationFactory.DEF_VAL_FOR_FORMAT;
    }

    @Override
    protected boolean getDefValForHonestMode() {
        return MultiplicationFactory.DEF_VAL_FOR_HONESTMODE;
    }

    @Override
    protected boolean getDefValForStopWatchMode() {
        return MultiplicationFactory.DEF_VAL_FOR_STOPWATCHMODE;
    }

    //==============================================================================================

    @Override
    protected String getTrainingKind() {
        return mResources.getString(R.string.multiplication);
    }

    @Override
    protected String getKindKey() {
        return MultiplicationFactory.KIND_KEY;
    }

    @Override
    protected String getAmountKey() {
        return MultiplicationFactory.AMOUNT_KEY;
    }

    @Override
    protected String getVisTimeKey() {
        return MultiplicationFactory.VISTIME_KEY;
    }

    @Override
    protected String getFormatKey() {
        return MultiplicationFactory.FORMAT_KEY;
    }

    @Override
    protected String getHonestModeKey() {
        return MultiplicationFactory.HONESTMODE_KEY;
    }

    @Override
    protected String getStopwatchModeKey() {
        return MultiplicationFactory.STOPWATCH_MODE_KEY;
    }
}
