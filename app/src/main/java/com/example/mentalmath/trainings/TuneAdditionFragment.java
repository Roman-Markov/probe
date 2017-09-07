package com.example.mentalmath.trainings;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SeekBarPreference;
import android.util.Log;
import android.view.View;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.08.2017.
 * Shows kind of addition trainings.
 */

public class TuneAdditionFragment extends PreferenceFragmentCompat {

    SeekBarPreference pref;
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.addition_prefs);
        pref = (SeekBarPreference) getPreferenceManager().findPreference("additionQuantity");
        pref.setMin(2);
    }

}
