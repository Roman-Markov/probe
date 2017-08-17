package com.example.mentalmath;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Роман on 16.08.2017.
 */

public class TrainingFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.training, container, false);

        // for test
        String choice = getActivity().getIntent().getStringExtra(MainFragment.KEY_KIND_OF_TRAININGS);
        TextView example = (TextView) result.findViewById(R.id.example);
        example.setText(choice);

        return result;
    }
}
