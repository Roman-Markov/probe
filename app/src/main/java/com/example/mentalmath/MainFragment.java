package com.example.mentalmath;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.trainings.ChooseSubTrainActivity;

/**
 * Fragments which is dynamically attached to {@link MainActivity} to show choice of types of
 * trainings e. g. arithmetic, equations, polynomials and  so on. After clicking appropriate button
 * it can start {@link ChooseSubTrainActivity} and pass it kind of training.
 */
public class MainFragment extends Fragment {

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
        switch (v.getId()) {
            case R.id.ariphmetics:
                kind = Constants.I_KIND_ARITHMETICS;
                break;
            case R.id.equations:
                kind = Constants.I_KIND_EQUATIONS;
                break;
            case R.id.matrix:
                kind = Constants.I_KIND_MATRIX;
                break;
            case R.id.multi_equations:
                kind = Constants.I_KIND_MULTI_EQUATIONS;
                break;
            case R.id.polynomials:
                kind = Constants.I_KIND_POLYNOMIALS;
                break;
            default:
                //todo: handle more smartly this case
                kind = Constants.I_KIND_ARITHMETICS;
                Log.e(getClass().getSimpleName(), "Unknown type of training fragment:");
        }

        Intent i = new Intent(getActivity(), ChooseSubTrainActivity.class);
        i.putExtra(Constants.KEY_KIND_OF_TRAININGS, kind);
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
