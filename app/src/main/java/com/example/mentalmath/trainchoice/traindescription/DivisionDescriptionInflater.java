package com.example.mentalmath.trainchoice.traindescription;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.example.mentalmath.R;
import com.example.mentalmath.core.Helper;
import com.example.mentalmath.trainings.trainfactory.DivisionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Роман on 12.05.2018.
 */

public class DivisionDescriptionInflater extends ADescriptionInflater {

    protected Map<String, String> mKindOfDivisorPrefMap = new HashMap<>();

    public DivisionDescriptionInflater(int kind) {
        super(kind);
    }

    @Override
    protected void addComplexityKindDescription(TextView tv) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);

        String dividendRange = mKindPrefMap.get(pref.getString(mResources.getString(R.string.dividendRangeListKey), DivisionFactory.DEF_VAL_FOR_DIVIDEND));
        String temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.dividend));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(dividendRange, R.color.green, tv))
                .append("\n"));

        String divisorRange = mKindOfDivisorPrefMap.get(pref.getString(mResources.getString(R.string.divisorRangeListKey), DivisionFactory.DEF_VAL_FOR_DIVISOR));
        // TODO: 12.05.2018 think about usage the same check for other preferences
        if (divisorRange == null) {
            divisorRange = DivisionFactory.DEF_VAL_FOR_DIVISOR;
        }
        temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.divisor));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(divisorRange, R.color.green, tv))
                .append("\n"));
    }

    @Override
    protected void addSpecificDescription(TextView tv) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);

        Boolean withReminder = pref.getBoolean(mResources.getString(R.string.divisionCbWithReminderKey), DivisionFactory.DEF_VAL_FOR_REMINDER_MODE);
        String reminderMode = withReminder ? mResources.getString(R.string.yes) : mResources.getString(R.string.no);
        String temp = mResources.getString(R.string.displayingSettingsFormat, mResources.getString(R.string.reminderMode));
        tv.append(Helper.insertStringToStart(temp, Helper.paintString(reminderMode, R.color.green, tv))
                .append("\n"));
    }

    protected void fillMaps() {
        super.fillMaps();

        String[] listOfValues;
        String[] listOfIds;

        listOfValues = mResources.getStringArray(R.array.divisionNumber);
        listOfIds = mResources.getStringArray(R.array.divisorId);
        fillPrefMap(listOfIds, listOfValues, mKindOfDivisorPrefMap);
    }

    //==============================================================================================

    // used to fill mKindPrefMap with dividend value
    @Override
    protected int getKindArray() {
        return R.array.divisionNumber;
    }

    // used to fill mKindPrefMap with dividend key
    @Override
    protected int getKindArrayId() {
        return R.array.dividendId;
    }

    //==============================================================================================

    // Stub, not used
    @Override
    protected String getDefValForKind() {
        return "";
    }

    @Override
    protected String getDefValForAmount() {
        return DivisionFactory.DEF_VAL_FOR_AMOUNT;
    }

    @Override
    protected String getDefValForVisTime() {
        return DivisionFactory.DEF_VAL_FOR_VISTIME;
    }

    @Override
    protected String getDefValForFormat() {
        return DivisionFactory.DEF_VAL_FOR_FORMAT;
    }

    @Override
    protected boolean getDefValForHonestMode() {
        return DivisionFactory.DEF_VAL_FOR_HONESTMODE;
    }

    @Override
    protected boolean getDefValForStopWatchMode() {
        return DivisionFactory.DEF_VAL_FOR_STOPWATCHMODE;
    }

    //==============================================================================================

    @Override
    protected String getTrainingKind() {
        return mResources.getString(R.string.division);
    }

    // Stub, not used
    @Override
    protected String getKindKey() {
        return "";
    }

    @Override
    protected String getAmountKey() {
        return DivisionFactory.AMOUNT_KEY;
    }

    @Override
    protected String getVisTimeKey() {
        return DivisionFactory.VISTIME_KEY;
    }

    @Override
    protected String getFormatKey() {
        return DivisionFactory.FORMAT_KEY;
    }

    @Override
    protected String getHonestModeKey() {
        return DivisionFactory.HONESTMODE_KEY;
    }

    @Override
    protected String getStopwatchModeKey() {
        return DivisionFactory.STOPWATCH_MODE_KEY;
    }
}
