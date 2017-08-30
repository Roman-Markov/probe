package com.example.mentalmath.trainings;

/**
 * Created by Роман on 18.08.2017.
 */

public class Stopwatch {

    private long m_startTime;
    private int m_timePassed;
    private boolean isRunning = false;

    public void start() {
        clear();
        resume();
    }

    public void stop() {
        isRunning = false;
        m_timePassed += System.currentTimeMillis() - m_startTime;
    }

    public void resume() {
        isRunning = true;
        m_startTime = System.currentTimeMillis();
    }

    public void clear() {
        isRunning = false;
        m_timePassed = 0;
        m_startTime = 0;
    }

    public int[] getTimePassed() {
        int timePassed = 0;
        if (isRunning) {
            timePassed = (int) (System.currentTimeMillis() - m_startTime) + m_timePassed;
        } else {
            timePassed = m_timePassed;
        }
        int[] time = new int[2];
        time[0] = (int) (timePassed/1000);
        time[1] = (int) ((int) (timePassed%1000)/10);
        return time;
    }
}
