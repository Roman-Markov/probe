package com.example.mentalmath.trainings;

/**
 * Created by Роман on 09.09.2017.
 */

public interface ITraining {

    void startTraining();

    void startExample();

    void pause ();

    void resume();

    void stopExample();

    void stopTrain();

    boolean shouldProceed();

    /**
     * shows wright result comparing with answer form user
     */
    public void showRightExampleResult();

    void hideInputMethod();

    void resetFocus();
}
