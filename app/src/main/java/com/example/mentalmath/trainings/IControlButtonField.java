package com.example.mentalmath.trainings;

import android.widget.Button;

/**
 * Created by Роман on 30.09.2017.
 */

public interface IControlButtonField extends IField {

    public Button getStartButton();

    public Button getOkButton();

    public Button getPauseButton();

    public Button getRightButton();

    public Button getWrongButton();
}
