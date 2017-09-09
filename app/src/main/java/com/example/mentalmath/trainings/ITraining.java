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
}
