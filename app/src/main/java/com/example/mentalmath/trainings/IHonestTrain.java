package com.example.mentalmath.trainings;

/**
 * Created by Роман on 30.09.2017.
 */

public interface IHonestTrain extends ITraining {

    public static final String RIGHT = "right";
    public static final String WRONG = "wrong";

    /**
     * Should only be called in honest mode. You should pass {@link IHonestTrain#RIGHT} or
     * {@link IHonestTrain#WRONG} constant
     * @param answer - {@link IHonestTrain#RIGHT} or {@link IHonestTrain#WRONG} constant
     */
    void handleResult(String answer);
}
