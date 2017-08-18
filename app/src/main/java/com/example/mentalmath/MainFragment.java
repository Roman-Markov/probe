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
import android.widget.Button;

import static com.example.mentalmath.TrainingFragment.Trainings.*;

public class MainFragment extends Fragment
    implements View.OnClickListener {

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
        View result = inflater.inflate(R.layout.main, container, false);

        Button nnXm = (Button) result.findViewById(R.id.NNxM);
        Button nnXmm = (Button) result.findViewById(R.id.NNxMM);
        Button nnnXmm = (Button) result.findViewById(R.id.NNNxMM);
        nnXm.setOnClickListener(this);
        nnXmm.setOnClickListener(this);
        nnnXmm.setOnClickListener(this);
        return result;
    }

    @Override
    public void onClick(View v) {
        TrainingFragment.Trainings kind;
        switch (v.getId()) {
            case R.id.NNxM:
                kind = NNxM;
                break;
            case R.id.NNxMM:
                kind = NNxMM;
                break;
            case R.id.NNNxMM:
                kind = NNNxMM;
                break;
            default:
                kind = NNxMM;
        }

        Intent i = new Intent(getActivity(), TrainingActivity.class);
        i.putExtra(KEY_KIND_OF_TRAININGS, kind.toString());
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
