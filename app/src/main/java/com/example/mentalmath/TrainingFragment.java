package com.example.mentalmath;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TrainingFragment extends Fragment implements View.OnClickListener {

    private TextView m_example;
    private EditText m_responce;
    private Button m_okButton;
    private Button m_startButton;
    private TextView m_timeView;
    private TextView m_timeViewTotal;
    private Stopwatch m_stopwatch;
    private Stopwatch m_swTotal;
    private ExampleGenerator m_generator;
    private Handler handler = new Handler();
    private Runnable m_uiUpdate;
    private int m_counter = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.training, container, false);

        String choice = getActivity().getIntent().getStringExtra(MainFragment.KEY_KIND_OF_TRAININGS);
        Trainings train = Trainings.valueOf(choice);
        m_generator = train.getGenerator();

        m_example = (TextView) result.findViewById(R.id.example);
        m_responce = (EditText) result.findViewById(R.id.result);
        m_okButton = (Button) result.findViewById(R.id.okButton);
        m_okButton.setOnClickListener(this);
        m_startButton = (Button) result.findViewById(R.id.startButton);
        m_startButton.setOnClickListener(this);
        m_timeView = (TextView) result.findViewById(R.id.stopwatch);
        m_timeViewTotal = (TextView) result.findViewById(R.id.swTotal);
        m_timeViewTotal.setText(R.string.initTime);
        m_timeView.setText(R.string.initTime);
        m_stopwatch = new Stopwatch();
        m_swTotal = new Stopwatch();
        m_uiUpdate = new Runnable() {
            @Override
            public void run() {
                updateUI();
                handler.postDelayed(this, 10);
            }
        };

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
            if (m_counter < getNumberOfExamples()) {
                startExample();
                m_counter++;
            } else {
                endSession();
            }

        } else if (v.getId() == R.id.startButton) {
            m_startButton.setVisibility(View.GONE);
            m_okButton.setVisibility(View.VISIBLE);
            startSession();
            startExample();
            m_counter++;
        }
    }

    public void updateUI() {
        int[] time = m_stopwatch.getTimePassed();
        String result = String.format("%d:%02d", time[0], time[1]);
        time = m_swTotal.getTimePassed();
        String total = String.format("%d:%02d", time[0], time[1]);
        m_timeViewTotal.setText(total);
        m_timeView.setText(result);
    }

    public void startExample() {

        m_example.setText(m_generator.generateExample());
        m_stopwatch.start();
        handler.removeCallbacks(m_uiUpdate);
        handler.post(m_uiUpdate);
    }

    public void startSession() {
        m_swTotal.start();
    }

    public void endSession() {
        handler.removeCallbacks(m_uiUpdate);
        m_counter = 0;
        m_stopwatch.clear();
        m_swTotal.clear();
        m_timeViewTotal.setText(R.string.initTime);
        m_timeView.setText(R.string.initTime);
        m_example.setText("");
        m_okButton.setVisibility(View.GONE);
        m_startButton.setVisibility(View.VISIBLE);
    }

    public int getNumberOfExamples() {

        // hardcoded yet
        return 3;
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
