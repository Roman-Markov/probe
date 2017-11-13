package com.example.mentalmath.trainings;

import android.util.Log;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;

/**
 * Created by Роман on 24.08.2017.
 */

public class AdditionBuilder extends ArithmeticBuilder {

    public final static String A15_15_ID = "1-5_p_1-5";
    public final static String A69_15_ID = "6-9_p_1-5";
    public final static String A69_69_ID = "6-9_p_6-9";

    public final static String A2N_1N_ID = "2n_p_1n";
    public final static String A2N_2N_ID = "2n_p_2n";
    public final static String A3N_2N_ID = "3n_p_2n";
    public final static String A3N_3N_ID = "3n_p_3n";
    public final static String A4N_4N_ID = "4n_p_4n";
    public final static String A5N_5N_ID = "5n_p_5n";
    public final static String A6N_6N_ID = "6n_p_6n";
    public final static String A7N_7N_ID = "7n_p_7n";
    public final static String A8N_8N_ID = "8n_p_8n";
    public final static String A9N_9N_ID = "9n_p_9n";
    public final static String A10N_10N_ID = "10n_p_10n";
    public final static String A11N_11N_ID = "11n_p_11n";
    public final static String A12N_12N_ID = "12n_p_12n";


    public static IExampleBuilder getAdditionBuilder(String id) {
        switch (id) {
            case A15_15_ID:
                return new AdditionBuilder(1, 5, 1, 5, A15_15_ID);
            case A69_15_ID:
                return new AdditionBuilder(6, 9, 1, 5, A69_15_ID);
            case A69_69_ID:
                return new AdditionBuilder(6, 9, 6, 9, A69_69_ID);

            case A2N_1N_ID:
                return new AdditionBuilder(2, 1, A2N_1N_ID);
            case A2N_2N_ID:
                return new AdditionBuilder(2, 2, A2N_2N_ID);
            case A3N_2N_ID:
                return new AdditionBuilder(3, 2, A3N_2N_ID);
            case A3N_3N_ID:
                return new AdditionBuilder(3, 3, A3N_3N_ID);
            case A4N_4N_ID:
                return new AdditionBuilder(4, 4, A4N_4N_ID);
            case A5N_5N_ID:
                return new AdditionBuilder(5, 5, A5N_5N_ID);
            case A6N_6N_ID:
                return new AdditionBuilder(6, 6, A6N_6N_ID);
            case A7N_7N_ID:
                return new AdditionBuilder(7, 7, A7N_7N_ID);
            case A8N_8N_ID:
                return new AdditionBuilder(8, 8, A8N_8N_ID);
            case A9N_9N_ID:
                return new AdditionBuilder(9, 9, A9N_9N_ID);
            case A10N_10N_ID:
                return new AdditionBuilder(10, 10, A10N_10N_ID);
            case A11N_11N_ID:
                return new AdditionBuilder(11, 11, A11N_11N_ID);
            case A12N_12N_ID:
                return new AdditionBuilder(12, 12, A12N_12N_ID);
            default:
                Log.e(AdditionBuilder.class.getSimpleName(), "There is no appropriate ID for kind of training: " + id);
                return new SimpleExampleBuilder();
        }
    }

    private long mNumberOfDigit1;
    private long mNumberOfDigit2;

    private long mFirstLowBound;
    private long mFirstHighBound;

    private long mSecondLowBound;
    private long mSecondHighBound;


    AdditionBuilder(int n, int m, String name) {
        super(name);
        mNumberOfDigit1 = n;
        mNumberOfDigit2 = m;
    }

    AdditionBuilder(long firstLowBound, long firstHighBound,
                    long secondLowBound, long secondHighBound, String name) {
        super(name);
        mFirstLowBound = firstLowBound;
        mFirstHighBound = firstHighBound;
        mSecondLowBound = secondLowBound;
        mSecondHighBound = secondHighBound;
    }

    @Override
    public String generateExample() {
        long[] result = new long[2];
        if (mNumberOfDigit1 > 0 && mNumberOfDigit2 > 0) {
            result = generateRandoms(mNumberOfDigit1, mNumberOfDigit2);
        }  else if (mFirstHighBound > 0 && mSecondHighBound > 0) {
            result = generateRandomFromRange(mFirstLowBound, mFirstHighBound, mSecondLowBound, mSecondHighBound);
        } else {
            Log.e(getClass().getSimpleName(), "Can't generate example.");
        }
        long big = result[1];
        long little = result[0];
        mResult = big + little;
        mCurrentAnswer = Long.toString(mResult);
        if (mFormat == ROW_FORMAT) {
            return Helper.mGlobalContext.getString(R.string.rowFormat, big, little);
        } else {
            return Helper.mGlobalContext.getString(R.string.columnFormat, big, little);
        }
    }
}
