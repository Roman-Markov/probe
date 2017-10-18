package com.example.mentalmath.trainings;

import android.util.Log;

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
        if (str != null && !"".equals(str)) {
            int result = 0;
            try {
                result = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return false;
            }
            return result == 2;
        }
        return false;
    }

    @Override
    public String getCurrentAnswer() {
        return "2";
    }
}
