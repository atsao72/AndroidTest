package com.mathchallenge.rook;

import com.mathchallenge.rook.R;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

//For list to change speed.
/*    <ListPreference
        android:title="Change Speed"
        android:key="speedList"
        android:summary="Change the time between equations"
        android:entries="@array/seconds"
        android:entryValues="@array/values"
        ></ListPreference>
*/
public class Preferences extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		Preference resetButton = (Preference) findPreference("reset");
	
		resetButton.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
	             Intent i = new Intent(Preferences.this, AnswerInputDialogActivity.class);
	             startActivity(i);
	             return true;
	         }
		});
	}
	
}
