package com.androidtest.austin;

import com.androidtest.austin.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

	Button startButton;
	Button howToButton;
	Button optionsButton;
	Button levelButton;
	//public static boolean[] levelsLocked = new boolean[20];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		startButton = (Button) findViewById(R.id.startButton);
		levelButton = (Button) findViewById(R.id.levelSelectButton);
		howToButton = (Button) findViewById(R.id.howToButton);
		optionsButton = (Button) findViewById(R.id.PrefsButton);
		
		/*for(int i=0;i<levelsLocked.length;i++){
			levelsLocked[i]=false;
		}*/
		//LevelSelectActivity levelActivity = new LevelSelectActivity();
		//levelActivity.setActivity();
		
	startButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent startGame = new Intent("com.androidtest.austin.MAINACTIVITY");
			if(LevelSelectActivity.level>Splash.MAX_LEVELS){
				Intent finish = new Intent(MenuActivity.this, FinishedActivity.class);
				startActivity(finish);					
			}
			else{
				startActivity(startGame);
			}
		}
	});
	
	howToButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent howTo = new Intent("com.androidtest.austin.HOWTO");
			startActivity(howTo);
		}
	});
	
	levelButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent startLevelMenu = new Intent("com.androidtest.austin.LEVELSELECT");
			startActivity(startLevelMenu);
		}
	});
	
	optionsButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent optionsIntent = new Intent("com.androidtest.austin.PREFERENCES");
			startActivity(optionsIntent);
		}
	});
	
	}

}
