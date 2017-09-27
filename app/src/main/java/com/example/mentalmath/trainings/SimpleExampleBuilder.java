package com.example.mentalmath.trainings;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleExampleBuilder implements IExampleBuilder {
    @Override
    public String generateExample() {
        return "1 + 1";
    }

    @Override
    public boolean checkResult(String str) {
        int result = Integer.parseInt(str);
        return result == 2;
    }
}
