package com.example.mentalmath.trainings;

/**
 * Created by Роман on 09.09.2017.
 */

public interface IAnswerField extends IField{

    /**
     * prepares fields (e. g. several text fields) for user answer input
     * before set of exercises.
     */
    public void prepareField();

    /**
     * actions which perform on pause
     */
    public void pause();

    /**
     * actions which perform on resume()
     */
    public void resume();


    /**
     * @return string representation of answer
     */
    public String getAnswer();

    /**
     * reset value from fields
     */
    public void clean();

    /**
     * shows wright result comparing with answer from user
     * @param answer - result from user
     */
    public void showRightResult(final String answer);

}
