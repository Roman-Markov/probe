package com.example.mentalmath.trainings;

/**
 * Created by Роман on 09.09.2017.
 */

public interface ITraining {

    public void startTraining();

    public void startExample();

    public void pause ();

    public void resume();

    public void stopExample();

    public void stopTrain();

    public boolean shouldProceed();

    /**
     * shows wright result comparing with answer form user
     */
    public void showRightExampleResult();

    public void handleResult(String result);
}
