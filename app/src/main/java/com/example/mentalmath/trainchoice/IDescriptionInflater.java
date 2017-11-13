package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by Роман on 13.11.2017.
 */

public interface IDescriptionInflater {

    public void fillDescription(TextView tv);

    public void init(Activity activity);

    public int getKind();
}
