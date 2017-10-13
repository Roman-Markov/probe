package com.example.mentalmath.trainings;

/**
 * Created by Роман on 09.09.2017.
 */

public interface IStopWatchField extends IField {

    public void start();

    public void startExample();

    public void resume();

    public void pause();

    public void stopExample();

    public void stopAll();

    public String getCurrentExampleTime();

    public String getTotalTime();

}
