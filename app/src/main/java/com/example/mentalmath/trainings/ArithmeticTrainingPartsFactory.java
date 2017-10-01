package com.example.mentalmath.trainings;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Роман on 27.09.2017.
 */

public class ArithmeticTrainingPartsFactory extends ATrainingPartsAbstractFactory {

    public ArithmeticTrainingPartsFactory(Fragment fragment, LayoutInflater inflater, ViewGroup container,
                                          IStopWatch stopWatch) {
        super(fragment, inflater, container, stopWatch);
    }

    @Override
    public IExampleBuilder getExampleBuilder() {

        // todo
        return new SimpleExampleBuilder();
    }
}
