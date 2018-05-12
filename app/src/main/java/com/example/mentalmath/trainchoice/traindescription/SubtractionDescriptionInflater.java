package com.example.mentalmath.trainchoice.traindescription;

import com.example.mentalmath.R;
import com.example.mentalmath.trainings.trainfactory.SubtractionFactory;

/**
 * Created by Роман on 13.11.2017.
 */

public class SubtractionDescriptionInflater extends ADescriptionInflater {

    public SubtractionDescriptionInflater(int kind) {
        super(kind);
    }

    //==============================================================================================
    
    @Override
    protected int getKindArray() {
        return R.array.subtractionTrainString;
    }

    @Override
    protected int getKindArrayId() {
        return R.array.subtractionTrainStringId;
    }

    //==============================================================================================

    @Override
    protected String getDefValForKind() {
        return SubtractionFactory.DEF_VAL_FOR_KIND;
    }

    @Override
    protected String getDefValForAmount() {
        return SubtractionFactory.DEF_VAL_FOR_AMOUNT;
    }

    @Override
    protected String getDefValForVisTime() {
        return SubtractionFactory.DEF_VAL_FOR_VISTIME;
    }

    @Override
    protected String getDefValForFormat() {
        return SubtractionFactory.DEF_VAL_FOR_FORMAT;
    }

    @Override
    protected boolean getDefValForHonestMode() {
        return SubtractionFactory.DEF_VAL_FOR_HONESTMODE;
    }

    @Override
    protected boolean getDefValForStopWatchMode() {
        return SubtractionFactory.DEF_VAL_FOR_STOPWATCHMODE;
    }

    //==============================================================================================

    @Override
    protected String getTrainingKind() {
        return mResources.getString(R.string.subtraction);
    }

    @Override
    protected String getKindKey() {
        return SubtractionFactory.KIND_KEY;
    }

    @Override
    protected String getAmountKey() {
        return SubtractionFactory.AMOUNT_KEY;
    }

    @Override
    protected String getVisTimeKey() {
        return SubtractionFactory.VISTIME_KEY;
    }

    @Override
    protected String getFormatKey() {
        return SubtractionFactory.FORMAT_KEY;
    }

    @Override
    protected String getHonestModeKey() {
        return SubtractionFactory.HONESTMODE_KEY;
    }

    @Override
    protected String getStopwatchModeKey() {
        return SubtractionFactory.STOPWATCH_MODE_KEY;
    }
}
