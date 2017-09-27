package com.example.mentalmath.trainings;

import android.widget.LinearLayout;

/**
 * Created by Роман on 27.09.2017.
 */

public class ArithmeticTrainingPartsFactory extends ATrainingPartsAbstractFactory {

    public ArithmeticTrainingPartsFactory(LinearLayout layoutForStopWatcher,
                                         LinearLayout layoutForExampleDisplay,
                                         LinearLayout layoutForAnswerField,
                                         LinearLayout layoutForSessionResultField) {
        super(layoutForStopWatcher,
                layoutForExampleDisplay,
                layoutForAnswerField,
                layoutForSessionResultField);
    }

    @Override
    public IExampleBuilder getExampleBuilder() {
        return new SimpleExampleBuilder();
    }
}
