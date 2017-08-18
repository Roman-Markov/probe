package com.example.mentalmath;

import java.util.Random;

/**
 * Created by Роман on 18.08.2017.
 */

public class MultiplicationGenerator implements ExampleGenerator {

    private int m_numberOfDigit1;
    private int m_numberOfDigit2;
    private long m_result;

    MultiplicationGenerator(int n, int m) {
        m_numberOfDigit1 = n;
        m_numberOfDigit2 = m;
    }

    @Override
    public String generateExample() {
        Random random = new Random();
        long min = Math.round(Math.pow(10, m_numberOfDigit1 -1));
        long max = Math.round(Math.pow(10, m_numberOfDigit1));
        int dif = (int) (max - min);

        int n = Math.round(random.nextInt(dif) + min);

        min = Math.round(Math.pow(10, m_numberOfDigit2 -1));
        max = Math.round(Math.pow(10, m_numberOfDigit2));
        dif = (int) (max - min);

        int m = Math.round(random.nextInt(dif) + min + 1);

        m_result = n * m;

        return String.format("%d * %d", n, m);
    }

    @Override
    public boolean checkResult(String str) {
        long result = Long.valueOf(str);
        return result == m_result;
    }
}
