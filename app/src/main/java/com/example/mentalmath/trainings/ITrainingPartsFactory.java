package com.example.mentalmath.trainings;

/**
 * Created by Роман on 27.09.2017.
 */

public interface ITrainingPartsFactory {

    public IStopWatchField getStopWatcherField();

    public IExampleDisplay getExampleDisplay();

    public IAnswerField getAnswerField();

    public ISessionResultField getSessionResultField();

    public IExampleBuilder getExampleBuilder();

}
