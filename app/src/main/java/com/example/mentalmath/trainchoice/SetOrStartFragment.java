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
import com.example.mentalmath.trainchoice.traindescription.AdditionDescriptionInflater;
import com.example.mentalmath.trainchoice.traindescription.DivisionDescriptionInflater;
import com.example.mentalmath.trainchoice.traindescription.MultiplicationDescriptionInflater;
import com.example.mentalmath.trainchoice.traindescription.SubtractionDescriptionInflater;
import com.example.mentalmath.trainings.TrainingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.mentalmath.core.Constants.*;

/**
 * Created by Роман on 31.08.2017.
 */

public class SetOrStartFragment extends ChooseSubTrainFragment {

    private IDescriptionInflater mDescriptionInflater;

    @BindView(R.id.description)
    TextView mTextView;
    private Unbinder unbinder;

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
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onStart() {
        mDescriptionInflater.fillDescription(mTextView);
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                mDescriptionInflater = new MultiplicationDescriptionInflater(I_KIND_ARITH_MULTIPLICATION);
                break;
            case I_KIND_ARITH_DIVISION:
                mDescriptionInflater = new DivisionDescriptionInflater(I_KIND_ARITH_DIVISION);
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
