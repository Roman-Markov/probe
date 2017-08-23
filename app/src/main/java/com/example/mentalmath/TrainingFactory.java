package com.example.mentalmath;

/**
 * Created by Роман on 23.08.2017.
 */

public class TrainingFactory {
    public final static int NNxM = 0;
    public final static int NNxMM = 1;
    public final static int NNNxMM = 2;

    private static TrainingFactory mInstance;

    public static TrainingFactory getmInstance() {
        if (mInstance == null) {
            return new TrainingFactory();
        }
        return mInstance;
    }

    private TrainingFactory(){};

    public BaseExampleBuilder getGenerator(int aType) {
        BaseExampleBuilder generator = null;
        switch (aType) {
            case NNxM:
                generator = new MultiplicationBuilder(2, 1, "NNXM");
                break;
            case NNxMM:
                generator = new MultiplicationBuilder(2, 2, "NNXMM");
                break;
            case NNNxMM:
                generator = new MultiplicationBuilder(3, 2, "NNNXMM");
                break;
        }
        return generator;
    }
}
