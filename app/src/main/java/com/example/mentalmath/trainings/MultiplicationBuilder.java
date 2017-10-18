package com.example.mentalmath.trainings;

/**
 * Created by Роман on 18.08.2017.
 */

public class MultiplicationBuilder extends ArithmeticBuilder {

    private int mNumberOfDigit1;
    private int mNumberOfDigit2;

    MultiplicationBuilder(int n, int m, String name) {
        super(name);
        mNumberOfDigit1 = n;
        mNumberOfDigit2 = m;
    }

    @Override
    public String generateExample() {
        long[] result = generateRandoms(mNumberOfDigit1, mNumberOfDigit2);
        long big = result[0];
        long little = result[1];

        mResult = big * little;
        mCurrentAnswer = Long.toString(mResult);

        return String.format("%d * %d", big, little);
    }

    @Override
    public String getCurrentAnswer() {
        return null;
    }
}
