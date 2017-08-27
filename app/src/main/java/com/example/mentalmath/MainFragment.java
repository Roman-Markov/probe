package com.example.mentalmath;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.trainings.ChooseSubTrainActivity;
import com.example.mentalmath.trainings.ChooseSubTrainFragmentFactory;

public class MainFragment extends Fragment {

    public static final String KEY_KIND_OF_TRAININGS = "train";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.main_choice, container, false);

        return result;
    }


    public void launchTrain(View v) {
        int kind = 0;
//        switch (v.getId()) {
//            case R.id.NxM:
//                kind = ExampleBuilderFactory.N_MULT_M;
//                break;
//            case R.id.NNxM:
//                kind = ExampleBuilderFactory.NN_MULT_M;
//                break;
//            case R.id.NNxMM:
//                kind = ExampleBuilderFactory.NN_MULT_MM;
//                break;
//            case R.id.NNNxM:
//                kind = ExampleBuilderFactory.NNN_MULT_M;
//                break;
//            case R.id.NNNxMM:
//                kind = ExampleBuilderFactory.NNN_MULT_MM;
//                break;
//            case R.id.NNNxMMM:
//                kind = ExampleBuilderFactory.NNN_MULT_MMM;
//                break;
//            case R.id.n_plus_m:
//                kind = ExampleBuilderFactory.N_PLUS_M;
//                break;
//            case R.id.nn_plus_m:
//                kind = ExampleBuilderFactory.NN_PLUS_M;
//                break;
//            case R.id.nn_plus_mm:
//                kind = ExampleBuilderFactory.NN_PLUS_MM;
//                break;
//            case R.id.nnn_plus_mm:
//                kind = ExampleBuilderFactory.NNN_PLUS_MM;
//                break;
//            case R.id.nnn_plus_mmm:
//                kind = ExampleBuilderFactory.NNN_PLUS_MMM;
//                break;
//            default:
//                kind = ExampleBuilderFactory.N_PLUS_M;
//        }

        switch (v.getId()) {
            case R.id.ariphmetics:
                kind = ChooseSubTrainFragmentFactory.I_ARIPHMETICS;
                break;
            case R.id.equations:
                kind = ChooseSubTrainFragmentFactory.I_EQUATIONS;
                break;
            case R.id.matrix:
                kind = ChooseSubTrainFragmentFactory.I_MATRIX;
                break;
            case R.id.multi_equations:
                kind = ChooseSubTrainFragmentFactory.I_MULTI_EQATIONS;
                break;
            case R.id.polynomials:
                kind = ChooseSubTrainFragmentFactory.I_POLYNOMIALS;
                break;
            default:
                kind = ChooseSubTrainFragmentFactory.I_ARIPHMETICS;
        }

        Intent i = new Intent(getActivity(), ChooseSubTrainActivity.class);
        i.putExtra(KEY_KIND_OF_TRAININGS, kind);
        startActivity(i);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.statistics) {
            startActivity(new Intent(getActivity(), ChoiceStatisticsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
