package com.example.mentalmath.trainings;

/**
 * Created by Роман on 09.09.2017.
 */

public interface IStopWatch {

    public void start();

    public void resume();

    public void pause();

    public void stop();

    public long getCurrentTime();

}
