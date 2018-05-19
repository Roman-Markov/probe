package com.example.mentalmath.trainings;

/**
 * Created by Роман on 27.09.2017.
 */

public interface ITrainingPartsFactory {

    IStopWatchField getStopWatcherField();

    IExampleDisplay getExampleDisplay();

    IControlButtonField getButtonField();

    IAnswerField getAnswerField();

    ISessionResultField getSessionResultField();

    IExampleBuilder getExampleBuilder();

    int getAmountOfExamples();

    boolean isHonestModeEnabled();

    boolean isStopwatchVisible();
}
