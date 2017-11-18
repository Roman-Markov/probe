package com.example.mentalmath.trainchoice;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;
import com.example.mentalmath.trainings.MultiplicationFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Роман on 13.11.2017.
 */

public class MultiplicationDescriptionInflater extends ADescriptionInflater {

    private Map<String, String> mKindPrefMap = new HashMap<>();
    private Map<String, String> mAmountPrefMap = new HashMap<>();
    private Map<String, String> mVisTimePrefMap = new HashMap<>();
    private Map<String, String> mFormatPrefMap = new HashMap<>();

    public MultiplicationDescriptionInflater(int kind) {
        super(kind);
    }

    @Override
    public void fillDescription(TextView tv) {
        tv.setText("");

        tv.append(mResources.getString(R.string.currentSettings));

        String temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.kind));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(
                mResources.getString(R.string.multiplication), R.color.green, tv))
                .append("\n"));

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);

        String type = mKindPrefMap.get(pref.getString(MultiplicationFactory.KIND_KEY, MultiplicationFactory.DEF_VAL_FOR_KIND));
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.type));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(type, R.color.green, tv))
                .append("\n"));

        String amount = mAmountPrefMap.get(pref.getString(MultiplicationFactory.AMOUNT_KEY, MultiplicationFactory.DEF_VAL_FOR_AMOUNT));
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.amount));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(amount, R.color.green, tv))
                .append("\n"));

        String vistime = mVisTimePrefMap.get(pref.getString(MultiplicationFactory.VISTIME_KEY, MultiplicationFactory.DEF_VAL_FOR_VISTIME));
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.visibility));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(vistime, R.color.green, tv))
                .append("\n"));

        String format = mFormatPrefMap.get(pref.getString(MultiplicationFactory.FORMAT_KEY, MultiplicationFactory.DEF_VAL_FOR_FORMAT));
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.format));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(format, R.color.green, tv))
                .append("\n"));

        Boolean isHonest = pref.getBoolean(MultiplicationFactory.HONESTMODE_KEY, false);
        String honestMode = isHonest? mResources.getString(R.string.yes) : mResources.getString(R.string.no);
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.honestMode));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(honestMode, R.color.green, tv))
                .append("\n"));
    }

    @Override
    protected void fillMaps() {
        String[] listOfValues;
        String[] listOfIds;

        listOfValues = mResources.getStringArray(R.array.multiplicationTrainString);
        listOfIds = mResources.getStringArray(R.array.multiplicationTrainStringId);
        fillPrefMap(listOfIds, listOfValues, mKindPrefMap);

        listOfValues = mResources.getStringArray(R.array.numberOfExamplesStrings);
        listOfIds = mResources.getStringArray(R.array.numberOfExamplesStringsId);
        fillPrefMap(listOfIds, listOfValues, mAmountPrefMap);

        listOfValues = mResources.getStringArray(R.array.visTime);
        listOfIds = mResources.getStringArray(R.array.visTimeId);
        fillPrefMap(listOfIds, listOfValues, mVisTimePrefMap);

        listOfValues = mResources.getStringArray(R.array.formatExample);
        listOfIds = mResources.getStringArray(R.array.formatExampleId);
        fillPrefMap(listOfIds, listOfValues, mFormatPrefMap);
    }

    private <T> void fillPrefMap(T[] listOfIds, T[] listOfValues, Map<T, T> mPrefMap) {
        for(int i = 0; i < listOfIds.length; i++) {
            mPrefMap.put(listOfIds[i], listOfValues[i]);
        }
    }
}
