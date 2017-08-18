package com.example.mentalmath;

import android.app.Fragment;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class TrainingFragment extends Fragment implements View.OnClickListener {

    public static final String KEY_RESULT_SESSION = "session result";
    public static final String KEY_EXAMPLE = "text from example";
    public static final String NNXM = "NNxM";
    public static final String NNXMM = "NNxMM";
    public static final String NNNXMM = "NNNxMM";

    private TextView m_example;
    private EditText m_responce;
    private TextView m_timeView;
    private TextView m_timeViewTotal;
    private TextView m_sessionResultView;
    private Button m_okButton;
    private Button m_startButton;
    private boolean m_isFirstrunning = false;
    private boolean m_isSessionRunning = false;

    private Stopwatch m_stopwatch;
    private Stopwatch m_swTotal;
    private ExampleGenerator m_generator;
    private Handler m_handler = new Handler();
    private Runnable m_uiUpdate;
    private DataBaseHelper db = null;
    private AsyncTask<ContentValues, Void, Void> m_task = null;

    private int m_counter = 0;
    private int m_rightCounter = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        m_isFirstrunning = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        db = DataBaseHelper.getInstance(getActivity());

        View result = inflater.inflate(R.layout.training, container, false);

        String choice = getActivity().getIntent().getStringExtra(MainFragment.KEY_KIND_OF_TRAININGS);
        Trainings train = Trainings.valueOf(choice);
        m_generator = train.getGenerator();

        m_example = (TextView) result.findViewById(R.id.example);
        m_sessionResultView = (TextView) result.findViewById(R.id.sessionResult);
        m_responce = (EditText) result.findViewById(R.id.result);

        m_okButton = (Button) result.findViewById(R.id.okButton);
        m_okButton.setOnClickListener(this);
        m_startButton = (Button) result.findViewById(R.id.startButton);
        m_startButton.setOnClickListener(this);

        m_timeView = (TextView) result.findViewById(R.id.stopwatch);
        m_timeViewTotal = (TextView) result.findViewById(R.id.swTotal);

        if (m_isFirstrunning) {
            m_timeViewTotal.setText(R.string.initTime);
            m_timeView.setText(R.string.initTime);

            m_stopwatch = new Stopwatch();
            m_swTotal = new Stopwatch();
            m_uiUpdate = new Runnable() {
                @Override
                public void run() {
                    updateUI();
                    m_handler.postDelayed(this, 10);
                }
            };
            m_isFirstrunning = false;
        }
        if (savedInstanceState != null) {
            restrore(savedInstanceState);
        }

        return result;
    }

    @Override
    public void onStop() {
//        if (m_task != null) {
//            m_task.cancel(false);
//        }
        m_handler.removeCallbacks(m_uiUpdate);
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_RESULT_SESSION, m_sessionResultView.getText().toString());
        outState.putString(KEY_EXAMPLE, m_example.getText().toString());
    }

    public void restrore(Bundle savedInstanceState) {
        String temp = savedInstanceState.getString(KEY_EXAMPLE);
        if (temp == null) {
            m_example.setText("");
        } else {
            m_example.setText(temp);
        }
        temp = savedInstanceState.getString(KEY_RESULT_SESSION);
        if (temp == null) {
            m_sessionResultView.setText("");
        } else {
            m_sessionResultView.setText(temp);
        }
        m_handler.post(m_uiUpdate);
        if (m_isSessionRunning) {
            m_startButton.setVisibility(View.GONE);
            m_okButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.okButton) {
            String result = m_responce.getText().toString();
            boolean isRight = m_generator.checkResult(result);
            if (isRight) {
                m_rightCounter++;
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
        String result = String.format(getString(R.string.stopwatchFormat), time[0], time[1]);
        time = m_swTotal.getTimePassed();
        String total = String.format(getString(R.string.stopwatchFormat), time[0], time[1]);
        m_timeViewTotal.setText(total);
        m_timeView.setText(result);
    }

    public void startExample() {

        m_example.setText(m_generator.generateExample());
        m_stopwatch.start();
        m_handler.removeCallbacks(m_uiUpdate);
        m_handler.post(m_uiUpdate);

    }

    public void startSession() {
        m_isSessionRunning = true;
        m_swTotal.start();
        m_sessionResultView.setText("");

    }

    public void endSession() {
        m_isSessionRunning = false;
        m_handler.removeCallbacks(m_uiUpdate);
        m_counter = 0;

        if (m_rightCounter != 0) {
            m_sessionResultView.setText(formResultSession());
        } else {
            m_sessionResultView.setText(getString(R.string.noRightAnswers));
        }
        m_rightCounter = 0;

        m_stopwatch.clear();
        m_swTotal.clear();

        m_timeViewTotal.setText(R.string.initTime);
        m_timeView.setText(R.string.initTime);
        m_example.setText("");
        m_okButton.setVisibility(View.GONE);
        m_startButton.setVisibility(View.VISIBLE);
    }

    private String formResultSession() {
        int[] time = m_swTotal.getTimePassed();
        double totalTime = time[0] + ((double) time[1] / 100);
        double averageTime = 0;
        if (m_rightCounter != 0) {
            averageTime = totalTime / m_rightCounter;
            insertResultToDB(averageTime);
        }
        String total = String.format(getString(R.string.stopwatchFormat), time[0], time[1]);

        StringBuilder sb = new StringBuilder();
        sb.append("Result:\n");

        sb.append(String.format(getString(R.string.rightAnswers),
                m_rightCounter, getNumberOfExamples()));
        sb.append(String.format(getString(R.string.totalTime), total));
        sb.append(String.format(getString(R.string.averageTime), doubleInTime(averageTime)));
        return sb.toString();
    }

    private String doubleInTime(double time) {
        int intPart = (int) time;
        int fractionalPart = (int) Math.round((time - intPart) * 100);
        return String.format(getString(R.string.stopwatchFormat), intPart, fractionalPart);
    }

    public int getNumberOfExamples() {

        // hardcoded yet
        return 2;
    }

    public void insertResultToDB(double average) {

        String date = DateUtils.formatDateTime(getActivity(), new Date().getTime(),
                DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_SHOW_TIME);

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.TYPE, m_generator.getName());
        values.put(DataBaseHelper.DATE, date);
        values.put(DataBaseHelper.TIME, average);
        m_task = new InsertTask();
        m_task.execute(values);
    }

    enum Trainings {
        NNxM, NNxMM, NNNxMM;

        public ExampleGenerator getGenerator() {
            ExampleGenerator generator = null;
            switch (this) {
                case NNxM:
                    generator = new MultiplicationGenerator(2, 1, NNXM);
                    break;
                case NNxMM:
                    generator = new MultiplicationGenerator(2, 2, NNXMM);
                    break;
                case NNNxMM:
                    generator = new MultiplicationGenerator(3, 2, NNNXMM);
                    break;
            }
            return generator;
        }
    }

    protected class InsertTask extends AsyncTask<ContentValues, Void, Void> {

        @Override
        protected Void doInBackground(ContentValues... params) {
            db.getWritableDatabase().insert(DataBaseHelper.TABLE,
                    DataBaseHelper.TYPE, params[0]);
            return null;
        }
    }
}
