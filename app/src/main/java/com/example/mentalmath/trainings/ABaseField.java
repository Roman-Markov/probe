package com.example.mentalmath.trainings;

import android.text.Layout;
import android.widget.LinearLayout;

/**
 * Created by Роман on 30.09.2017.
 */

public abstract class ABaseField implements IField{

    protected LinearLayout mLayout;

    public ABaseField(LinearLayout layout) {
        mLayout = layout;
    }

    @Override
    public LinearLayout getLayout() {
        return mLayout;
    }
}
