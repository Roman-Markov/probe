package com.example.mentalmath.trainings;

/**
 * Created by Роман on 09.09.2017.
 */

public interface ISessionResult {

    public void addExampleResult();

    public void addCommonResult (String commonResult);

    public void reset ();
}
