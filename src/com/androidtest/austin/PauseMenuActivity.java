package com.androidtest.austin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class PauseMenuActivity extends Activity{
	
	Button resumeButton;
	Button optionsButton;
	Button menuButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pause_menu);
		resumeButton = (Button) findViewById(R.id.resumeButton);
		optionsButton = (Button) findViewById(R.id.optionsButton);
		menuButton = (Button) findViewById(R.id.quitButton);
		//final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		resumeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		optionsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("com.androidtest.austin.PREFERENCES");
				startActivity(intent);
			}
		});
		
		menuButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PauseMenuActivity.this, MenuActivity.class);
				startActivity(intent);
			}
		});
	}
	

}
