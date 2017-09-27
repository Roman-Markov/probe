package com.example.mentalmath.trainings;

/**
 * Created by Роман on 09.09.2017.
 */

public interface IAnswerField {

    /**
     * prepares fields (e. g. several text fields) for user answer input
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
     * deletes all widgets from field
     */
    public void resetField();

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
    public void showRightResult(String answer);

}
