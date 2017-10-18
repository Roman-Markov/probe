package com.example.mentalmath.trainings;

/**
 * Created by Роман on 09.09.2017.
 */

public interface IExampleDisplay extends IField {

    public void showNewExample();

    public void hideExample();

    public void showExample();

    public void showHiddenExample();

    public String getCurrentAnswer();

    public boolean checkResult(String userAnswer);
}
