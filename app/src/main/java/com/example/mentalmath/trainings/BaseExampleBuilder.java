package com.example.mentalmath.trainings;

/**
 * Created by Роман on 23.08.2017.
 */

public abstract class BaseExampleBuilder implements IExampleBuilder {

    String mName;
    public BaseExampleBuilder(String name) {
        mName = name;
    }
    @Override
    abstract public String generateExample();

    @Override
    abstract public boolean checkResult(String str);

    public String getName() {
        return mName;
    }
}
