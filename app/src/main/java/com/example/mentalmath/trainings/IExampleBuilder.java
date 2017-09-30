package com.example.mentalmath.trainings;

/**
 * Created by Роман on 18.08.2017.
 */

public interface IExampleBuilder {

    String generateExample();

    boolean checkResult(String str);

    public String getCurrentAnswer();
}
