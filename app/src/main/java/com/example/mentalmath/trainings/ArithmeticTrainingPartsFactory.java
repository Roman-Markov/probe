package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.widget.LinearLayout;

/**
 * Created by Роман on 27.09.2017.
 */

public class ArithmeticTrainingPartsFactory extends ATrainingPartsAbstractFactory {

    public ArithmeticTrainingPartsFactory(Fragment fragment, LinearLayout parentLayout,
                                          IStopWatcher stopWatch) {
        super(fragment, parentLayout, stopWatch);
    }

    @Override
    public IExampleBuilder getExampleBuilder() {

        // todo
        return new SimpleExampleBuilder();
    }
}
