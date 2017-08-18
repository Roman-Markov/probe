package com.example.mentalmath;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Роман on 16.08.2017.
 */

public class TrainingFragment extends Fragment implements View.OnClickListener {

    private TextView m_example;
    private EditText m_responce;
    private Button m_okButton;
    private TextView m_timeView;
    private Stopwatch m_stopwatch;
    private ExampleGenerator m_generator;
    private Handler handler = new Handler();
    private Runnable m_uiUpdate;
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
        Trainings train = Trainings.valueOf(choice);
        m_generator = train.getGenerator();

        m_example = (TextView) result.findViewById(R.id.example);
        m_responce = (EditText) result.findViewById(R.id.result);
        m_okButton = (Button) result.findViewById(R.id.okButton);
        m_okButton.setOnClickListener(this);
        m_timeView = (TextView) result.findViewById(R.id.stopwatch);
        m_stopwatch = new Stopwatch();
        m_uiUpdate = new Runnable() {
            @Override
            public void run() {
                updateUI();
                handler.postDelayed(this, 10);
            }
        };

        startExample();

        return result;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.okButton) {
            String result = m_responce.getText().toString();
            boolean isRight = m_generator.checkResult(result);
            if (isRight) {
                Toast.makeText(getActivity(), R.string.right, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), R.string.wrong, Toast.LENGTH_SHORT).show();
            }
            m_responce.setText("");
            startExample();
        }
    }

    public void updateUI() {
        int[] time = m_stopwatch.getTimePassed();
        String result = String.format("%d:%02d", time[0], time[1]);
        m_timeView.setText(result);
    }

    public void startExample() {

        m_example.setText(m_generator.generateExample());
        m_stopwatch.start();
        handler.removeCallbacks(m_uiUpdate);
        handler.post(m_uiUpdate);
    }

    public enum Trainings {
        NNxM, NNxMM, NNNxMM;
        public ExampleGenerator getGenerator() {
            ExampleGenerator generator = null;
            switch (this) {
                case NNxM:
                    generator = new MultiplicationGenerator(2, 1);
                    break;
                case NNxMM:
                    generator = new MultiplicationGenerator(2, 2);
                    break;
                case NNNxMM:
                    generator = new MultiplicationGenerator(3, 2);
                    break;
            }
            return generator;
        }
    }


}
