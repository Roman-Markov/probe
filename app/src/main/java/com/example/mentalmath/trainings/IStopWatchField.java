package com.example.mentalmath.trainings;

import android.text.Layout;
import android.view.ViewGroup;

/**
 * Created by Роман on 09.09.2017.
 */

public interface IStopWatchField extends IField {

    void start();

    void startExample();

    void resume();

    void pause();

    void stopExample();

    void stopAll();

    String getCurrentExampleTime();

    String getTotalTime();

}
