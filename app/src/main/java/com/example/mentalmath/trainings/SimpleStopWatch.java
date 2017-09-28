package com.example.mentalmath.trainings;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleStopWatch implements IStopWatcher {

    private long m_startTime;
    private int m_timePassed;
    private boolean isRunning = false;


    @Override
    public void start() {
        stop();
        resume();
    }

    @Override
    public void stop() {
        isRunning = false;
        m_timePassed = 0;
        m_startTime = 0;
    }

    @Override
    public void resume() {
        isRunning = true;
        m_startTime = System.currentTimeMillis();
    }

    @Override
    public void pause() {
        isRunning = false;
        m_timePassed += System.currentTimeMillis() - m_startTime;
    }

    @Override
    public long getCurrentTime() {
        int timePassed = 0;
        if (isRunning) {
            timePassed = (int) (System.currentTimeMillis() - m_startTime) + m_timePassed;
        } else {
            timePassed = m_timePassed;
        }
        return timePassed;
    }
}
