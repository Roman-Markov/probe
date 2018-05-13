package com.example.mentalmath.trainings.examplegenerator;

import android.preference.PreferenceManager;
import android.util.Log;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;
import com.example.mentalmath.trainings.IExampleBuilder;

/**
 * Created by Роман on 13.05.2018.
 */

public class DivisionBuilder extends ArithmeticBuilder {

    private long mReminder = 0;

    public static final String SPLITTER = ":";

    public static IExampleBuilder create(String dividendId, String divisorId) {

        int dividendIndex = Helper.getIndexFromStringArray(R.array.dividendId, dividendId);
        int divisorIndex = Helper.getIndexFromStringArray(R.array.divisorId, divisorId);
        if (dividendIndex < divisorIndex) {
            Log.e("DivisionBuilder", "Dividend range less than divisor range");
            throw new RuntimeException("Dividend range less than divisor range");
        }
        if (dividendIndex == -1) {
            dividendIndex = 0;
            divisorIndex = 0;
        } else if (divisorIndex == -1) {
            divisorIndex = 0;
        }
        int numberOfDividendDigit = dividendIndex + 1;
        int numberOfDivisorDigit = divisorIndex + 1;
        String name = Integer.toString(numberOfDividendDigit) + "n : "
                + Integer.toString(numberOfDivisorDigit) + "n";
        return new DivisionBuilder(numberOfDividendDigit, numberOfDivisorDigit, name);
    }

    @Override
    public boolean checkResult(String str) {
        String[] values = str.split(SPLITTER);
        Long result = Long.decode(values[0].trim());
        Long reminder = Long.decode(values[1].trim());
        return result == mResult && reminder == mReminder;
    }

    public DivisionBuilder(int n, int m, String name) {
        super(n, m, name);
    }

    @Override
    public String generateExample() {
        mReminder = 0;
        long dividend = 0;
        long divisor;
        if (mNumberOfDigit1 == mNumberOfDigit2) {
            long min = Math.round(Math.pow(10, mNumberOfDigit2 -1));
            long max = Math.round(Math.pow(10, mNumberOfDigit2)) / 2;
            // for case when min = 1;
            min = Math.max(2, min);
            divisor = rnd(min, max - 1);
        } else {
            divisor = generateRandoms(mNumberOfDigit2)[0];
        }

        long max = Math.round(Math.pow(10, mNumberOfDigit1)) - 1;
        long maxResult = max / divisor;
        long min = Math.round(Math.pow(10, mNumberOfDigit1 - 1));
        long minResult = min/divisor + 1;
        minResult = Math.max(2, minResult);
        mResult = rnd(minResult, maxResult);

        boolean withReminder = PreferenceManager.getDefaultSharedPreferences(Helper.mGlobalContext)
                .getBoolean(Helper.mGlobalContext.getString(R.string.divisionCbWithReminderKey), false);
        if (withReminder) {
            dividend = divisor * mResult;
            long toMax = max - dividend;
            long maxReminder = Math.min(toMax, divisor -1);
            // for case when result is too near to max result value
            if (maxReminder < 2) {
                mReminder = maxReminder;
            } else {
                mReminder = rnd(1, maxReminder);
            }
            dividend += mReminder;
        } else {
            dividend = divisor * mResult;
        }
        mCurrentAnswer = Long.toString(mResult) + SPLITTER + Long.toString(mReminder);
        if (mFormat == IExampleBuilder.ROW_FORMAT) {
            return Helper.mGlobalContext.getString(R.string.divisionRowFormat, dividend, divisor);
        } else {
            return Helper.mGlobalContext.getString(R.string.divisionColumnFormat, dividend, divisor);
        }
    }
}
