package com.example.mentalmath.trainchoice;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.mentalmath.R;

/**
 * Created by Роман on 27.08.2017.
 */

public class TuneMultiplicationFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.addition_prefs);
    }
}
