package com.example.mentalmath.trainings.examplegenerator;

import android.util.Log;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;
import com.example.mentalmath.trainings.IExampleBuilder;
import com.example.mentalmath.trainings.SimpleExampleBuilder;


public class MultiplicationBuilder extends ArithmeticBuilder {

    public final static String X15_15_ID = "1-5_x_1-5";
    public final static String X69_15_ID = "6-9_x_1-5";
    public final static String X69_69_ID = "6-9_x_6-9";

    public final static String X2N_1N_ID = "2n_x_1n";
    public final static String X2N_2N_ID = "2n_x_2n";
    public final static String X3N_2N_ID = "3n_x_2n";
    public final static String X3N_3N_ID = "3n_x_3n";
    public final static String X4N_4N_ID = "4n_x_4n";
    public final static String X5N_5N_ID = "5n_x_5n";
    public final static String X6N_6N_ID = "6n_x_6n";


    public static IExampleBuilder create(String id) {
        switch (id) {
            case X15_15_ID:
                return new MultiplicationBuilder(1, 5, 1, 5, X15_15_ID);
            case X69_15_ID:
                return new MultiplicationBuilder(6, 9, 1, 5, X69_15_ID);
            case X69_69_ID:
                return new MultiplicationBuilder(6, 9, 6, 9, X69_69_ID);

            case X2N_1N_ID:
                return new MultiplicationBuilder(2, 1, X2N_1N_ID);
            case X2N_2N_ID:
                return new MultiplicationBuilder(2, 2, X2N_2N_ID);
            case X3N_2N_ID:
                return new MultiplicationBuilder(3, 2, X3N_2N_ID);
            case X3N_3N_ID:
                return new MultiplicationBuilder(3, 3, X3N_3N_ID);
            case X4N_4N_ID:
                return new MultiplicationBuilder(4, 4, X4N_4N_ID);
            case X5N_5N_ID:
                return new MultiplicationBuilder(5, 5, X5N_5N_ID);
            case X6N_6N_ID:
                return new MultiplicationBuilder(6, 6, X6N_6N_ID);
            default:
                Log.e(MultiplicationBuilder.class.getSimpleName(), "There is no appropriate ID for kind of training: " + id);
                return new SimpleExampleBuilder();
        }
    }

    public MultiplicationBuilder(int n, int m, String name) {
        super(n, m, name);
    }

    public MultiplicationBuilder(long firstLowBound, long firstHighBound,
                    long secondLowBound, long secondHighBound, String name) {
        super(firstLowBound, firstHighBound, secondLowBound, secondHighBound, name);
    }

    @Override
    public String generateExample() {
        long[] result = getNumbersForExample();
        long big = result[1];
        long little = result[0];
        mResult = big * little;
        mCurrentAnswer = Long.toString(mResult);
        if (mFormat == ROW_FORMAT) {
            return Helper.mGlobalContext.getString(R.string.multiplicationRowFormat, big, little);
        } else {
            return Helper.mGlobalContext.getString(R.string.multiplicationColumnFormat, big, little);
        }
    }
}
