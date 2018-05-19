package com.example.mentalmath.trainings.fields;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.trainings.ABaseField;
import com.example.mentalmath.trainings.ISessionResultField;

import java.util.ArrayList;

/**
 * Created by Роман on 27.09.2017.
 */

public class SimpleSessionResultField extends ABaseField implements ISessionResultField {

    TextView mSessionResultView;
    ListView mListView;

    ArrayList<String> mList = new ArrayList<String>();
    ArrayAdapter<String> mAdapter;

    int mExampleCounter;

    public SimpleSessionResultField (LayoutInflater inflater, ViewGroup container) {
        super(inflater, container, R.layout.session_result_field);
        init();
    }

    public SimpleSessionResultField (ViewGroup container) {
        super(container, R.id.sessionResultField);
        init();
    }

    private void init() {
        mSessionResultView = mLayout.findViewById(R.id.sessionResult);
        mListView = mLayout.findViewById(R.id.list);
        mAdapter = new ArrayAdapter<> (mLayout.getContext(), android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void resetFields(ViewGroup layout) {
        mLayout = layout;
        int visibility = mListView.getVisibility();
        mListView = mLayout.findViewById(R.id.list);
        mListView.setVisibility(visibility);
        mListView.setAdapter(mAdapter);

        visibility = mSessionResultView.getVisibility();
        CharSequence text = mSessionResultView.getText();
        mSessionResultView = mLayout.findViewById(R.id.sessionResult);
        mSessionResultView.setVisibility(visibility);
        mSessionResultView.setText(text);
        //mAdapter = (ArrayAdapter<String>) mListView.getAdapter();
//        if (mAdapter == null) {
//            mAdapter = new ArrayAdapter<> (mLayout.getContext(), android.R.layout.simple_list_item_1, mList);
//        }
//        mListView.setAdapter(mAdapter);
    }



    @Override
    public void addExampleResult(String time, boolean isRight) {
        mExampleCounter++;
        String result;
        if (isRight) {
            result = mLayout.getContext().getString(R.string.right);
        } else {
            result = mLayout.getContext().getString(R.string.wrong);
        }
        mAdapter.insert(String.format(mLayout.getContext().getString(R.string.exampleResultFormat),
                mExampleCounter, time, result.toLowerCase()),
                0);
    }

    @Override
    public void addCommonResult(String commonResult) {
        mAdapter.insert(commonResult, 0);
        mExampleCounter = 0;
    }

    @Override
    public void reset() {
        mAdapter.clear();
    }
}
