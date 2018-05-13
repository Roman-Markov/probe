package com.example.mentalmath.trainings;

import com.example.mentalmath.trainings.examplegenerator.AdditionBuilder;
import com.example.mentalmath.trainings.examplegenerator.BaseExampleBuilder;
import com.example.mentalmath.trainings.examplegenerator.MultiplicationBuilder;

/**
 * Created by Роман on 23.08.2017.
 */

public class ExampleBuilderFactory {

    public final static int N_MULT_M = 0;
    public final static int NN_MULT_M = 1;
    public final static int NN_MULT_MM = 2;
    public final static int NNN_MULT_M = 3;
    public final static int NNN_MULT_MM = 4;
    public final static int NNN_MULT_MMM = 5;

    public final static int N_PLUS_M        = 6;
    public final static int NN_PLUS_M       = 7;
    public final static int NN_PLUS_MM      = 8;
    public final static int NNN_PLUS_MM     = 9;
    public final static int NNN_PLUS_MMM    = 10;

    public final static String N_X_M        = "N_X_M";
    public final static String NN_X_M       = "NN_X_M";
    public final static String NN_X_MM      = "NN_X_MM";
    public final static String NNN_X_M      = "NNN_X_M";
    public final static String NNN_X_MM     = "NNN_X_MM";
    public final static String NNN_X_MMM    = "NNN_X_MMM";

    public final static String N_P_M        = "N_PLUS_M";
    public final static String NN_P_M       = "NN_PLUS_M";
    public final static String NN_P_MM      = "NN_PLUS_MM";
    public final static String NNN_P_MM     = "NNN_PLUS_MM";
    public final static String NNN_P_MMM    = "NNN_PLUS_MMM";

    private static ExampleBuilderFactory mInstance;

    public static ExampleBuilderFactory getmInstance() {
        if (mInstance == null) {
            return new ExampleBuilderFactory();
        }
        return mInstance;
    }

    private ExampleBuilderFactory(){};

    public BaseExampleBuilder getGenerator(int aType) {
        BaseExampleBuilder generator = null;
        switch (aType) {
            case N_MULT_M:
                generator = new MultiplicationBuilder(2, 1, N_X_M);
                break;
            case NN_MULT_M:
                generator = new MultiplicationBuilder(2, 1, NN_X_M);
                break;
            case NN_MULT_MM:
                generator = new MultiplicationBuilder(2, 2, NN_X_MM);
                break;
            case NNN_MULT_M:
                generator = new MultiplicationBuilder(3, 1, NNN_X_M);
                break;
            case NNN_MULT_MM:
                generator = new MultiplicationBuilder(3, 2, NNN_X_MM);
                break;
            case NNN_MULT_MMM:
                generator = new MultiplicationBuilder(3, 3, NNN_X_MMM);
                break;

            case N_PLUS_M:
                generator = new AdditionBuilder(1, 1, N_P_M);
                break;
            case NN_PLUS_M:
                generator = new AdditionBuilder(2, 1, NN_P_M);
                break;
            case NN_PLUS_MM:
                generator = new AdditionBuilder(2, 2, NN_P_MM);
                break;
            case NNN_PLUS_MM:
                generator = new AdditionBuilder(3, 2, NNN_P_MM);
                break;
            case NNN_PLUS_MMM:
                generator = new AdditionBuilder(3, 3, NNN_P_MMM);
                break;
        }
        return generator;
    }
}
