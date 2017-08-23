package com.example.mentalmath;

import java.util.Random;

/**
 * Created by Роман on 24.08.2017.
 */

public class AdditionBuilder extends AriphmeticBuilder {

    private int m_numberOfDigit1;
    private int m_numberOfDigit2;

    AdditionBuilder(int n, int m, String name) {
        super(name);
        m_numberOfDigit1 = n;
        m_numberOfDigit2 = m;
    }

    @Override
    public String generateExample() {
        long[] result = generateRandoms(m_numberOfDigit1, m_numberOfDigit2);
        long big = result[0];
        long little = result[1];
        m_result = big + little;
        return String.format("%d + %d", big, little);
    }
}
