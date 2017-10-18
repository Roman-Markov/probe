package com.example.mentalmath.trainings;

/**
 * Created by Роман on 18.08.2017.
 */

public interface IExampleBuilder {

    public static final int ROW_FORMAT = 1;
    public static final int COLUMN_FORMAT = 2;

    String generateExample();

    boolean checkResult(String str);

    public String getCurrentAnswer();
}
