package com.example.mentalmath.trainchoice.traindescription;

import com.example.mentalmath.R;
import com.example.mentalmath.trainings.trainfactory.AdditionFactory;

/**
 * Created by Роман on 13.11.2017.
 */

public class AdditionDescriptionInflater extends ADescriptionInflater {

    public AdditionDescriptionInflater(int kind) {
        super(kind);
    }

    //==============================================================================================

    @Override
    protected int getKindArray() {
        return R.array.additionTrainString;
    }

    @Override
    protected int getKindArrayId() {
        return R.array.additionTrainStringId;
    }

    //==============================================================================================

    @Override
    protected String getDefValForKind() {
        return AdditionFactory.DEF_VAL_FOR_KIND;
    }

    @Override
    protected String getDefValForAmount() {
        return AdditionFactory.DEF_VAL_FOR_AMOUNT;
    }

    @Override
    protected String getDefValForVisTime() {
        return AdditionFactory.DEF_VAL_FOR_VISTIME;
    }

    @Override
    protected String getDefValForFormat() {
        return AdditionFactory.DEF_VAL_FOR_FORMAT;
    }

    @Override
    protected boolean getDefValForHonestMode() {
        return AdditionFactory.DEF_VAL_FOR_HONESTMODE;
    }

    @Override
    protected boolean getDefValForStopWatchMode() {
        return AdditionFactory.DEF_VAL_FOR_STOPWATCHMODE;
    }

    //==============================================================================================

    @Override
    protected String getTrainingKind() {
        return mResources.getString(R.string.addition);
    }

    @Override
    protected String getKindKey() {
        return AdditionFactory.KIND_KEY;
    }

    @Override
    protected String getAmountKey() {
        return AdditionFactory.AMOUNT_KEY;
    }

    @Override
    protected String getVisTimeKey() {
        return AdditionFactory.VISTIME_KEY;
    }

    @Override
    protected String getFormatKey() {
        return AdditionFactory.FORMAT_KEY;
    }

    @Override
    protected String getHonestModeKey() {
        return AdditionFactory.HONESTMODE_KEY;
    }

    @Override
    protected String getStopwatchModeKey() {
        return AdditionFactory.STOPWATCH_MODE_KEY;
    }
}
