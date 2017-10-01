package com.example.mentalmath.trainings;

/**
 * Created by Роман on 09.09.2017.
 */

public interface IExampleDisplay extends IField {

    public void showNewExample();

    public void hideExample();

    public void showHiddenExample();

    public String getCurrentAnswer();

    public IExampleBuilder getExampleBuilder();
}
