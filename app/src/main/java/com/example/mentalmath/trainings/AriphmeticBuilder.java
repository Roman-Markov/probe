package com.example.mentalmath.trainings;

import java.util.Random;

/**
 * Created by Роман on 24.08.2017.
 */

public abstract class AriphmeticBuilder extends BaseExampleBuilder {

    protected long mResult;
    protected String mCurrentAnswer;

    public AriphmeticBuilder(String name) {
        super(name);
    }

    public long[] generateRandoms(long... digitNumber) {

        long[] result = new long[digitNumber.length];
        Random random = new Random();
        for (int i = 0; i < digitNumber.length; i++) {
            long min = Math.round(Math.pow(10, digitNumber[i] - 1)) + 1;
            long max = Math.round(Math.pow(10, digitNumber[i]));
            int dif = (int) (max - min);

            long temp = Math.round(random.nextInt(dif) + min);
            result[i] = temp;
        }

        return result;
    }

    @Override
    public boolean checkResult(String str) {
        long result = Long.valueOf(str);
        return result == mResult;
    }

    @Override
    public String getCurrentAnswer() {
        return mCurrentAnswer;
    }
}
