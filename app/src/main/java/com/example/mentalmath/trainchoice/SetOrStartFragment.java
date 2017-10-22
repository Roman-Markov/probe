package com.example.mentalmath.trainchoice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Constants;
import com.example.mentalmath.core.Helper;
import com.example.mentalmath.trainings.ExampleBuilderFactory;
import com.example.mentalmath.trainings.TrainingActivity;

import java.util.HashMap;
import java.util.Map;

import com.example.mentalmath.trainings.AdditionFactory;


/**
 * Created by Роман on 31.08.2017.
 */

public class SetOrStartFragment extends ChooseSubTrainFragment {

    private int mOptionKind;
    private Map<String, String> mKindPrefMap = new HashMap<>();
    private Map<String, String> mAmountPrefMap = new HashMap<>();
    private Map<String, String> mVisTimePrefMap = new HashMap<>();
    private Map<String, String> mFormatPrefMap = new HashMap<>();

    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOptionKind = getActivity().getIntent().getIntExtra(Constants.KEY_KIND_OF_OPTIONS, -1);
        init();
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.set_or_start, container, false);
        mTextView = v.findViewById(R.id.description);
        fillDescription(mTextView);

        return v;
    }

    @Override
    public void onStart() {
        fillDescription(mTextView);
        super.onStart();
    }

    private void init() {
        String[] listOfValues;
        String[] listOfIds;
        switch (mOptionKind) {
            case Constants.I_KIND_ARITH_ADDITION:
                fillAdditionMaps();
                listOfValues = getResources().getStringArray(R.array.additionTrainString);
                listOfIds = getResources().getStringArray(R.array.additionTrainStringId);
                fillPrefMap(listOfIds, listOfValues, mKindPrefMap);
                break;
            case Constants.I_KIND_ARITH_SUBTRACTION:
                break;
            case Constants.I_KIND_ARITH_MULTIPLICATION:
                break;
            case Constants.I_KIND_ARITH_DIVISION:
                break;
            default:
                Log.e(getClass().getSimpleName(), "Unknown type of arithmetic training: " + mOptionKind);
        }
    }

    private void fillAdditionMaps() {
        String[] listOfValues;
        String[] listOfIds;

        listOfValues = getResources().getStringArray(R.array.additionTrainString);
        listOfIds = getResources().getStringArray(R.array.additionTrainStringId);
        fillPrefMap(listOfIds, listOfValues, mKindPrefMap);

        listOfValues = getResources().getStringArray(R.array.numberOfExamplesStrings);
        listOfIds = getResources().getStringArray(R.array.numberOfExamplesStringsId);
        fillPrefMap(listOfIds, listOfValues, mAmountPrefMap);

        listOfValues = getResources().getStringArray(R.array.visTime);
        listOfIds = getResources().getStringArray(R.array.visTimeId);
        fillPrefMap(listOfIds, listOfValues, mVisTimePrefMap);

        listOfValues = getResources().getStringArray(R.array.formatExample);
        listOfIds = getResources().getStringArray(R.array.formatExampleId);
        fillPrefMap(listOfIds, listOfValues, mFormatPrefMap);
    }

    private <T> void fillPrefMap(T[] listOfIds, T[] listOfValues, Map<T, T> mPrefMap) {
        for(int i = 0; i < listOfIds.length; i++) {
            mPrefMap.put(listOfIds[i], listOfValues[i]);
        }
    }

    private void fillDescription(TextView tv) {
        tv.setText(getString(R.string.currentSettings));
        switch (mOptionKind) {
            case Constants.I_KIND_ARITH_ADDITION:
                showCurrentAdditionSettings(tv);
                break;
            case Constants.I_KIND_ARITH_SUBTRACTION:
                showCurrentSubtractionSettings(tv);
                break;
            case Constants.I_KIND_ARITH_MULTIPLICATION:
                showCurrentMultiplicationSettings(tv);
                break;
            case Constants.I_KIND_ARITH_DIVISION:
                showCurrentDivisionSettings(tv);
                break;
            default:
                Log.e(getClass().getSimpleName(), "Unknown type of arithmetic training: " + mOptionKind);
        }
    }

    private void showCurrentAdditionSettings(TextView tv) {

        tv.append(Helper.insertStringToStart(getString(R.string.kind), Helper.paintString(
                getString(R.string.addition), R.color.green, tv))
                .append("\n"));

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String type = mKindPrefMap.get(pref.getString(AdditionFactory.KIND_KEY, AdditionFactory.DEF_VAL_FOR_KIND));
        String amount = mAmountPrefMap.get(pref.getString(AdditionFactory.AMOUNT_KEY, AdditionFactory.DEF_VAL_FOR_AMOUNT));
        String vistime = mVisTimePrefMap.get(pref.getString(AdditionFactory.VISTIME_KEY, AdditionFactory.DEF_VAL_FOR_VISTIME));
        String format = mFormatPrefMap.get(pref.getString(AdditionFactory.FORMAT_KEY, AdditionFactory.DEF_VAL_FOR_FORMAT));

        tv.append(Helper.insertStringToStart(getString(R.string.type), Helper.paintString(
                type, R.color.green, tv))
                .append("\n"));
        tv.append(Helper.insertStringToStart(getString(R.string.amount), Helper.paintString(
                amount, R.color.green, tv))
                .append("\n"));
        tv.append(Helper.insertStringToStart(getString(R.string.visibility), Helper.paintString(
                vistime, R.color.green, tv))
                .append("\n"));
        tv.append(Helper.insertStringToStart(getString(R.string.format), Helper.paintString(
                format, R.color.green, tv))
                .append("\n"));

        Boolean isHonest = pref.getBoolean(AdditionFactory.HONESTMODE_KEY, false);
        String honestMode = isHonest? getString(R.string.yes) : getString(R.string.no);
        tv.append(Helper.insertStringToStart(getString(R.string.honestMode), Helper.paintString(
                honestMode, R.color.green, tv))
                .append("\n"));

    }

    private void showCurrentSubtractionSettings(TextView tv) {

    }

    private void showCurrentMultiplicationSettings(TextView tv) {

    }

    private void showCurrentDivisionSettings(TextView tv) {

    }

    @Override
    public void launchTrain(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.start:
                i = new Intent(getActivity(), TrainingActivity.class);
                //hardcoded yet
                i.putExtra(Constants.KEY_KIND_OF_TRAININGS, ExampleBuilderFactory.N_PLUS_M);
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
                i.putExtra(Constants.KEY_KIND_OF_OPTIONS, mOptionKind);
                break;
            default:
                Log.e(getClass().getSimpleName(), "Unknown id of button.");
                return;
        }
        startActivity(i);
    }

}
