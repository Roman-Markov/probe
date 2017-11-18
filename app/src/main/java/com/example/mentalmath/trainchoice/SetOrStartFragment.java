package com.example.mentalmath.trainchoice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Constants;
import com.example.mentalmath.trainings.ExampleBuilderFactory;
import com.example.mentalmath.trainings.TrainingActivity;


/**
 * Created by Роман on 31.08.2017.
 */

public class SetOrStartFragment extends ChooseSubTrainFragment {

    public static final int I_KIND_ARITHMETICS = 0;
    public static final int I_KIND_ARITH_ADDITION = 1;
    public static final int I_KIND_ARITH_SUBTRACTION = 2;
    public static final int I_KIND_ARITH_MULTIPLICATION = 3;
    public static final int I_KIND_ARITH_DIVISION = 4;

    private IDescriptionInflater mDescriptionInflater;

    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int optionKind = getActivity().getIntent().getIntExtra(Constants.KEY_KIND_OF_OPTIONS, -1);
        init(optionKind);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.set_or_start, container, false);
        mTextView = v.findViewById(R.id.description);

        return v;
    }

    @Override
    public void onStart() {
        mDescriptionInflater.fillDescription(mTextView);
        super.onStart();
    }

    private void init(int optionKind) {

        switch (optionKind) {
            case I_KIND_ARITH_ADDITION:
                mDescriptionInflater = new AdditionDescriptionInflater(I_KIND_ARITH_ADDITION);
                break;
            case I_KIND_ARITH_SUBTRACTION:
                mDescriptionInflater = new SubtractionDescriptionInflater(I_KIND_ARITH_SUBTRACTION);
                break;
            case I_KIND_ARITH_MULTIPLICATION:
                break;
            case I_KIND_ARITH_DIVISION:
                break;
            default:
                Log.e(getClass().getSimpleName(), "Unknown type of arithmetic training: " + optionKind);
        }
        mDescriptionInflater.init(getActivity());
    }

    @Override
    public void launchTrain(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.start:
                i = new Intent(getActivity(), TrainingActivity.class);
                //hardcoded yet
                i.putExtra(Constants.KEY_KIND_OF_TRAININGS, mDescriptionInflater.getKind());
                break;
            default:
                Log.e(getClass().getSimpleName(), "Unknown id of button.");
                return;
        }
        startActivity(i);
    }

    public void showOptions(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.set_options:
                i = new Intent(getActivity(), SetOptionActivity.class);
                i.putExtra(Constants.KEY_KIND_OF_OPTIONS, mDescriptionInflater.getKind());
                break;
            default:
                Log.e(getClass().getSimpleName(), "Unknown id of button.");
                return;
        }
        startActivity(i);
    }

}
