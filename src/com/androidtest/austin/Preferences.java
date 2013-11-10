package com.androidtest.austin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.androidtest.austin.R;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity{

	Preference resetButton, emailButton;
	CheckBoxPreference sound;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		resetButton = (Preference) findPreference("reset");
		emailButton = (Preference) findPreference("email");
		sound = (CheckBoxPreference) findPreference("sound");
		
		resetButton.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				AlertDialog alertDialog = new AlertDialog.Builder(Preferences.this).create();
				alertDialog.setTitle("Reset");
				alertDialog.setMessage("Are you sure you want to reset?");
				alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
					  public void onClick(DialogInterface dialog, int which) {
						  android.os.Process.killProcess(android.os.Process.myPid());
						    Editor editor = getSharedPreferences("clear_cache", Context.MODE_PRIVATE).edit();
						    editor.clear();
						    editor.commit();
						    trimCache(Preferences.this);
					  }
				});
				alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
					  public void onClick(DialogInterface dialog, int which) {
					  }
				});
				alertDialog.show();
				return true;
	         }
		});
		
		emailButton.setOnPreferenceClickListener(new OnPreferenceClickListener(){
			public boolean onPreferenceClick(Preference preference) {
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND); 
				emailIntent.setType("plain/text");
				String[] aEmailList = {"rook.customerservice@gmail.com"};
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
				startActivity(Intent.createChooser(emailIntent, "Send your email in:"));
	            return true;
	         }
		});
		
	}

	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}

	private static void trimCache(Context context) {
	    try {
	        File dir = context.getCacheDir();
	        if (dir != null && dir.isDirectory()) {
	            deleteDir(dir);

	        }
	    } catch (Exception e) {
	        // TODO: handle exception
	    }
	}


	private static boolean deleteDir(File dir) {
	    if (dir != null && dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }
	    // <uses-permission
	    // android:name="android.permission.CLEAR_APP_CACHE"></uses-permission>
	    // The directory is now empty so delete it
	    return dir.delete();
	}
	
}
