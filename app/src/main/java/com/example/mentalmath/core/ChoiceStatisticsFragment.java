package com.example.mentalmath.core;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalmath.R;


public class ChoiceStatisticsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.statistics_choice, container, false);

        return result;
    }


    public void showStatistics(View v) {
//        switch (v.getId()) {
//            case R.id.nn_x_m:
//                break;
//            case R.id.nn_x_mm:
//                break;
//            case R.id.nnn_x_mm:
//                break;
//        }
        startActivity(new Intent(getActivity(), StaticsticsActivity.class));
    }
}
