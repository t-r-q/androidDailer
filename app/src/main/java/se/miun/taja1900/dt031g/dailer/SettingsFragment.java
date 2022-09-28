package se.miun.taja1900.dt031g.dailer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragment {

    private static String delButtonKey = "del_button_preference";
    private static String listButtonOnResume = "saveList";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.root_preferences);
        Preference pref = findPreference(delButtonKey);
        pref.setOnPreferenceClickListener(preference -> {
            SharedPreferences preferences = getActivity().getSharedPreferences(DialActivity.callListName, Context.MODE_PRIVATE);
            preferences .edit().clear().apply();
            return true;
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean sharedPref = sharedPreferences.getBoolean(listButtonOnResume, true);
    }
}