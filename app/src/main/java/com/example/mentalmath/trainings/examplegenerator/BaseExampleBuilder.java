package com.example.mentalmath.trainings.examplegenerator;

import com.example.mentalmath.trainings.IExampleBuilder;

/**
 * Created by Роман on 23.08.2017.
 */

public abstract class BaseExampleBuilder implements IExampleBuilder {

    private String mName;
    public BaseExampleBuilder(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }
}
