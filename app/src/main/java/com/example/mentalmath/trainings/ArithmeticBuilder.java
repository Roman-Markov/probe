package com.example.mentalmath.trainings;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Роман on 24.08.2017.
 */

public abstract class ArithmeticBuilder extends BaseExampleBuilder {

    protected long mResult;
    protected String mCurrentAnswer;

    protected int mFormat;

    protected long mNumberOfDigit1;
    protected long mNumberOfDigit2;

    protected long mFirstLowBound;
    protected long mFirstHighBound;

    protected long mSecondLowBound;
    protected long mSecondHighBound;

    ArithmeticBuilder(int n, int m, String name) {
        super(name);
        mNumberOfDigit1 = n;
        mNumberOfDigit2 = m;
    }

    ArithmeticBuilder(long firstLowBound, long firstHighBound,
                          long secondLowBound, long secondHighBound, String name) {
        super(name);
        mFirstLowBound = firstLowBound;
        mFirstHighBound = firstHighBound;
        mSecondLowBound = secondLowBound;
        mSecondHighBound = secondHighBound;
    }

    public void setFormat(int format) {
        mFormat = format;
    }

    public long[] getNumbersForExample() {
        long[] result = new long[2];
        if (mNumberOfDigit1 > 0 && mNumberOfDigit2 > 0) {
            result = generateRandoms(mNumberOfDigit1, mNumberOfDigit2);
        } else if (mFirstHighBound > 0 && mSecondHighBound > 0) {
            result = generateRandomFromRange(mFirstLowBound, mFirstHighBound, mSecondLowBound, mSecondHighBound);
        } else {
            Log.e(getClass().getSimpleName(), "Can't generate example.");
        }
        return result;
    }

    protected long[] generateRandoms(long... digitNumber) {

        long[] result = new long[digitNumber.length];
        for (int i = 0; i < digitNumber.length; i++) {
            long min = Math.round(Math.pow(10, digitNumber[i] - 1)) + 1;
            long max = Math.round(Math.pow(10, digitNumber[i]));

            long temp = rnd(min, max);

            result[i] = temp;
        }
        swapBigLittle(result);

        return result;
    }

    /**
     * sort passed array in ascending order, that is max number will be the last
     * @param result
     */
    protected void swapBigLittle(long[] result){
        Arrays.sort(result);
    }

    /**
     * @param firstLowBound
     * @param firstHighBound
     * @param secondLowBound
     * @param secondHighBound
     * @return long[] array, where array[0] - random from first range, array[1] random from second range.
     */
    protected long[] generateRandomFromRange(long firstLowBound, long firstHighBound,
                                             long secondLowBound, long secondHighBound) {

        if (firstHighBound <= firstLowBound || secondHighBound <= secondLowBound) {
            Log.e(getClass().getSimpleName(), "High bound less or equals to low bound");
            return null;
        }
        long[] result = new long[2];

        result[0] = rnd(firstLowBound, firstHighBound);
        result[1] = rnd(secondLowBound, secondHighBound);

        swapBigLittle(result);

        return result;
    }

    public static long rnd(long min, long max) {
        max -= min;
        Long temp = (long) (Math.random() * (max + 1)) + min;
        // for complexity random number shouldn't ends with 0
        while (temp%10 == 0) {
            temp = (long) (Math.random() * (max + 1)) + min;
        }
        return temp;
    }

    @Override
    public boolean checkResult(String str) {
        long result = 0;
        try {
            result = Long.valueOf(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return result == mResult;
    }

    @Override
    public String getCurrentAnswer() {
        return mCurrentAnswer;
    }
}
