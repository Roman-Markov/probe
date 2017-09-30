package com.example.mentalmath.trainings;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleSessionResultField extends ABaseField implements ISessionResultField {

    TextView mSessionResultView;

    public SimpleSessionResultField(LinearLayout parentLayout) {
        super((LinearLayout) parentLayout.findViewById(R.layout.session_result_field));
        mSessionResultView = mLayout.findViewById(R.id.sessionResult);
    }

    @Override
    public void addExampleResult() {
        // todo implement Bus pattern here
    }

    @Override
    public void addCommonResult(String commonResult) {

    }

    @Override
    public void reset() {

    }
}
