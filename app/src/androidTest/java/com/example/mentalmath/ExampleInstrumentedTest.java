package com.example.mentalmath;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.mentalmath.core.Helper;
import com.example.mentalmath.trainings.examplegenerator.DivisionBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    Context appContext;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.mentalmath", appContext.getPackageName());
    }

    @Test
    public void divisionBuilderTest() {
        initContext();
        Helper.mGlobalContext = appContext;
        PreferenceManager.getDefaultSharedPreferences(Helper.mGlobalContext).edit()
                .putBoolean(getString(R.string.divisionCbWithReminderKey), false).commit();

        DivisionBuilder builder;
        String result;
        String[] values;
        String dividendString;
        String divisorString;
        long dividend = 0;
        long divisor = 0;

        builder = new DivisionBuilder(4, 4, "division");
        for (int i = 0; i < 100; i++) {
            result = builder.generateExample();
            values = result.split(":");
            dividendString = values[0].trim();
            divisorString = values[1].trim();
            assertEquals("Unexpected string length", 4, dividendString.length());
            assertEquals("Unexpected string length", 4, divisorString.length());
            dividend = Long.decode(dividendString);
            divisor = Long.decode(divisorString);
            assertEquals("Reminder should be equal to 0.", 0, dividend % divisor);
        }

        builder = new DivisionBuilder(7, 4, "division");
        for (int i = 0; i < 100; i++) {
            result = builder.generateExample();
            values = result.split(":");
            dividendString = values[0].trim();
            divisorString = values[1].trim();
            assertEquals("Unexpected string length", 7, dividendString.length());
            assertEquals("Unexpected string length", 4, divisorString.length());
            dividend = Long.decode(values[0].trim());
            divisor = Long.decode(values[1].trim());
            assertEquals("Reminder should be equal to 0.", 0, dividend % divisor);
        }

        PreferenceManager.getDefaultSharedPreferences(Helper.mGlobalContext).edit()
                .putBoolean(getString(R.string.divisionCbWithReminderKey), true).commit();

        builder = new DivisionBuilder(6, 4, "division");
        for (int i = 0; i < 100; i++) {
            result = builder.generateExample();
            values = result.split(":");
            dividendString = values[0].trim();
            divisorString = values[1].trim();
            assertEquals("Unexpected string length", 6, dividendString.length());
            assertEquals("Unexpected string length", 4, divisorString.length());
            dividend = Long.decode(values[0].trim());
            divisor = Long.decode(values[1].trim());

            long divResult = dividend / divisor;
            long reminder = dividend % divisor;

            String answer = Long.toString(divResult) + DivisionBuilder.SPLITTER + Long.toString(reminder);
            assertTrue("Result of division must be right", builder.checkResult(answer));
        }

        builder = new DivisionBuilder(1, 1, "division");
        for (int i = 0; i < 100; i++) {
            result = builder.generateExample();
            values = result.split(":");
            dividendString = values[0].trim();
            divisorString = values[1].trim();
            assertEquals("Unexpected dividend length: " + dividendString + ":" +  divisorString,
                    1, dividendString.length());
            assertEquals("Unexpected divisor length: " + dividendString + ":" +  divisorString,
                    1, divisorString.length());
            dividend = Long.decode(values[0].trim());
            divisor = Long.decode(values[1].trim());

            long divResult = dividend / divisor;
            long reminder = dividend % divisor;

            String answer = Long.toString(divResult) + DivisionBuilder.SPLITTER + Long.toString(reminder);
            boolean isRight = builder.checkResult(answer);
            assertTrue("Result of division must be right: "  + dividendString + ":" +  divisorString,
                    builder.checkResult(answer));
        }
    }

    private void initContext() {
        this.appContext = InstrumentationRegistry.getTargetContext();
    }

    private String getString(@StringRes int id) {
        return appContext.getString(id);
    }
}
