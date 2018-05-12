package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.annotation.ArrayRes;
import android.support.annotation.IdRes;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;
import com.example.mentalmath.trainings.AdditionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Роман on 13.11.2017.
 */

abstract class ADescriptionInflater implements IDescriptionInflater {
    protected Resources mResources;
    protected Context mContext;
    protected int mKind;

    protected Map<String, String> mKindPrefMap = new HashMap<>();
    protected Map<String, String> mAmountPrefMap = new HashMap<>();
    protected Map<String, String> mVisTimePrefMap = new HashMap<>();
    protected Map<String, String> mFormatPrefMap = new HashMap<>();

    protected ADescriptionInflater(int kind) {
        mKind = kind;
    }

    @Override
    public void init(Activity activity) {
        mContext = activity;
        mResources = mContext.getResources();
        fillMaps();
    }

    @Override
    public int getKind() {
        return mKind;
    }

    @Override
    public void fillDescription(TextView tv) {
        tv.setText("");

        tv.append(mResources.getString(R.string.currentSettings));

        String temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.kind));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(
                getTrainingKind(), R.color.green, tv))
                .append("\n"));

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);

        String type = mKindPrefMap.get(pref.getString(getKindKey(), getDefValForKind()));
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.type));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(type, R.color.green, tv))
                .append("\n"));

        String amount = mAmountPrefMap.get(pref.getString(getAmountKey(), getDefValForAmount()));
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.amount));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(amount, R.color.green, tv))
                .append("\n"));

        String vistime = mVisTimePrefMap.get(pref.getString(getVisTimeKey(), getDefValForVisTime()));
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.visibility));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(vistime, R.color.green, tv))
                .append("\n"));

        String format = mFormatPrefMap.get(pref.getString(getFormatKey(), getDefValForFormat()));
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.format));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(format, R.color.green, tv))
                .append("\n"));

        Boolean isHonest = pref.getBoolean(getHonestModeKey(), getDefValForHonestMode());
        String honestMode = isHonest? mResources.getString(R.string.yes) : mResources.getString(R.string.no);
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.honestMode));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(honestMode, R.color.green, tv))
                .append("\n"));

        Boolean showStopwatch = pref.getBoolean(getStopwatchModeKey(), getDefValForStopWatchMode());
        String stopwatchMode = showStopwatch? mResources.getString(R.string.visible) : mResources.getString(R.string.notVisible);
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.stopwatchMode));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(stopwatchMode, R.color.green, tv))
                .append("\n"));
    }

    private void fillMaps() {
        String[] listOfValues;
        String[] listOfIds;

        listOfValues = mResources.getStringArray(getKindArray());
        listOfIds = mResources.getStringArray(getKindArrayId());
        fillPrefMap(listOfIds, listOfValues, mKindPrefMap);

        listOfValues = mResources.getStringArray(getNumberOfExamplesArray());
        listOfIds = mResources.getStringArray(getNumberOfExamplesArrayId());
        fillPrefMap(listOfIds, listOfValues, mAmountPrefMap);

        listOfValues = mResources.getStringArray(getVisTimeArray());
        listOfIds = mResources.getStringArray(getVisTimeArrayId());
        fillPrefMap(listOfIds, listOfValues, mVisTimePrefMap);

        listOfValues = mResources.getStringArray(getFormatExampleArray());
        listOfIds = mResources.getStringArray(getFormatExampleArrayId());
        fillPrefMap(listOfIds, listOfValues, mFormatPrefMap);
    }

    private <T> void fillPrefMap(T[] listOfIds, T[] listOfValues, Map<T, T> mPrefMap) {
        for(int i = 0; i < listOfIds.length; i++) {
            mPrefMap.put(listOfIds[i], listOfValues[i]);
        }
    }

    //==============================================================================================

    @ArrayRes
    protected abstract int getKindArray();
    @ArrayRes
    protected abstract int getKindArrayId();
    @ArrayRes
    protected int getNumberOfExamplesArray() {
        return R.array.numberOfExamplesStrings;
    }
    @ArrayRes
    protected int getNumberOfExamplesArrayId() {
        return R.array.numberOfExamplesStringsId;
    }
    @ArrayRes
    protected int getVisTimeArray() {
        return R.array.visTime;
    }
    @ArrayRes
    protected int getVisTimeArrayId() {
        return R.array.visTimeId;
    }
    @ArrayRes
    protected int getFormatExampleArray() {
        return R.array.formatExample;
    }
    @ArrayRes
    protected int getFormatExampleArrayId() {
        return R.array.formatExampleId;
    }

    //==============================================================================================

    protected abstract String getDefValForKind();

    protected abstract String getDefValForAmount();

    protected abstract String getDefValForVisTime();

    protected abstract String getDefValForFormat();

    protected abstract boolean getDefValForHonestMode();

    protected abstract boolean getDefValForStopWatchMode();

    //==============================================================================================

    protected abstract String getTrainingKind();

    protected abstract String getKindKey();

    protected abstract String getAmountKey();

    protected abstract String getVisTimeKey();

    protected abstract String getFormatKey();

    protected abstract String getHonestModeKey();

    protected abstract String getStopwatchModeKey();


}
