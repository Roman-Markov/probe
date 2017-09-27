package com.example.mentalmath.trainings;

/**
 * Created by Роман on 27.09.2017.
 */

public abstract class ATrainingPartsAbstractFactory implements ITrainingPartsFactory {

    @Override
    public IStopWatcherField getStopWatcherField(){return new SimpleStopWatcherField();}

    @Override
    public IExampleDisplay getExampleDisplay(){return new SimpleExampleDisplay();}

    @Override
    public IAnswerField getAnswerField(){return new SimpleAnswerField();}

    @Override
    public ISessionResultField getSessionResultField(){return new SimpleSessionResultField();}

    @Override
    public abstract IExampleBuilder getExampleBuilder();
}
