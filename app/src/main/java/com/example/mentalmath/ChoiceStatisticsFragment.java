package com.example.mentalmath;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ChoiceStatisticsFragment extends Fragment
        implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.statistics_choice, container, false);
        Button nnXm = (Button) result.findViewById(R.id.nn_x_m);
        Button nnXmm = (Button) result.findViewById(R.id.nn_x_mm);
        Button nnnXmm = (Button) result.findViewById(R.id.nnn_x_mm);

        nnXm.setOnClickListener(this);
        nnXmm.setOnClickListener(this);
        nnnXmm.setOnClickListener(this);

        return result;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nn_x_m:
                break;
            case R.id.nn_x_mm:
                break;
            case R.id.nnn_x_mm:
                break;
        }
        startActivity(new Intent(getActivity(), StaticsticsActivity.class));
    }
}
