package com.example.mentalmath.trainings;

import android.util.Log;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;



public class SubtractionBuilder extends ArithmeticBuilder {

    public final static String M15_15_ID = "1-5_m_1-5";
    public final static String M69_15_ID = "6-9_m_1-5";
    public final static String M69_69_ID = "6-9_m_6-9";

    public final static String M2N_1N_ID = "2n_m_1n";
    public final static String M2N_2N_ID = "2n_m_2n";
    public final static String M3N_2N_ID = "3n_m_2n";
    public final static String M3N_3N_ID = "3n_m_3n";
    public final static String M4N_4N_ID = "4n_m_4n";
    public final static String M5N_5N_ID = "5n_m_5n";
    public final static String M6N_6N_ID = "6n_m_6n";
    public final static String M7N_7N_ID = "7n_m_7n";
    public final static String M8N_8N_ID = "8n_m_8n";
    public final static String M9N_9N_ID = "9n_m_9n";
    public final static String M10N_10N_ID = "10n_m_10n";
    public final static String M11N_11N_ID = "11n_m_11n";
    public final static String M12N_12N_ID = "12n_m_12n";


    public static IExampleBuilder getSubtractionBuilder(String id) {
        switch (id) {
            case M15_15_ID:
                return new SubtractionBuilder(1, 5, 1, 5, M15_15_ID);
            case M69_15_ID:
                return new SubtractionBuilder(6, 9, 1, 5, M69_15_ID);
            case M69_69_ID:
                return new SubtractionBuilder(6, 9, 6, 9, M69_69_ID);

            case M2N_1N_ID:
                return new SubtractionBuilder(2, 1, M2N_1N_ID);
            case M2N_2N_ID:
                return new SubtractionBuilder(2, 2, M2N_2N_ID);
            case M3N_2N_ID:
                return new SubtractionBuilder(3, 2, M3N_2N_ID);
            case M3N_3N_ID:
                return new SubtractionBuilder(3, 3, M3N_3N_ID);
            case M4N_4N_ID:
                return new SubtractionBuilder(4, 4, M4N_4N_ID);
            case M5N_5N_ID:
                return new SubtractionBuilder(5, 5, M5N_5N_ID);
            case M6N_6N_ID:
                return new SubtractionBuilder(6, 6, M6N_6N_ID);
            case M7N_7N_ID:
                return new SubtractionBuilder(7, 7, M7N_7N_ID);
            case M8N_8N_ID:
                return new SubtractionBuilder(8, 8, M8N_8N_ID);
            case M9N_9N_ID:
                return new SubtractionBuilder(9, 9, M9N_9N_ID);
            case M10N_10N_ID:
                return new SubtractionBuilder(10, 10, M10N_10N_ID);
            case M11N_11N_ID:
                return new SubtractionBuilder(11, 11, M11N_11N_ID);
            case M12N_12N_ID:
                return new SubtractionBuilder(12, 12, M12N_12N_ID);
            default:
                Log.e(SubtractionBuilder.class.getSimpleName(), "There is no appropriate ID for kind of training: " + id);
                return new SimpleExampleBuilder();
        }
    }

    private long mNumberOfDigit1;
    private long mNumberOfDigit2;

    private long mFirstLowBound;
    private long mFirstHighBound;

    private long mSecondLowBound;
    private long mSecondHighBound;


    SubtractionBuilder(int n, int m, String name) {
        super(name);
        mNumberOfDigit1 = n;
        mNumberOfDigit2 = m;
    }

    SubtractionBuilder(long firstLowBound, long firstHighBound,
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
        mResult = big - little;
        mCurrentAnswer = Long.toString(mResult);
        if (mFormat == ROW_FORMAT) {
            return Helper.mGlobalContext.getString(R.string.subtractionRowFormat, big, little);
        } else {
            return Helper.mGlobalContext.getString(R.string.subtractionColumnFormat, big, little);
        }
    }
}
