package com.mathchallenge.rook;

import com.mathchallenge.rook.R;

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
	public static boolean[] levelsLocked = new boolean[8];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		startButton = (Button) findViewById(R.id.startButton);
		levelButton = (Button) findViewById(R.id.levelSelectButton);
		howToButton = (Button) findViewById(R.id.howToButton);
		optionsButton = (Button) findViewById(R.id.optionsButton);
		final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		/*for(int i=0;i<levelsLocked.length;i++){
			levelsLocked[i]=false;
		}*/
		//LevelSelectActivity levelActivity = new LevelSelectActivity();
		//levelActivity.setActivity();
		
	startButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent startGame = new Intent("com.mathchallenge.rook.MAINACTIVITY");
			int time = Integer.parseInt(getPrefs.getString("speedList", "3000"));
			LevelSelectActivity activity= new LevelSelectActivity();
			int level = activity.getLevel();
			Bundle bundleLevel = new Bundle();
			bundleLevel.putInt("level", level);
			Bundle bundle = new Bundle();
			bundle.putInt("time", time);
			startGame.putExtras(bundle);
			startGame.putExtras(bundleLevel);
			startActivity(startGame);
		}
	});
	
	howToButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
			alert.setTitle("How To Play");
			alert.setMessage("Each operation will show for 4 seconds. At the end, input the answer");
			alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			alert.show();
		}
	});
	
	levelButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent startLevelMenu = new Intent("com.mathchallenge.rook.LEVELSELECT");
			startActivity(startLevelMenu);
		}
	});
	
	optionsButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent openOptions = new Intent("com.mathchallenge.rook.PREFERENCES");
			startActivity(openOptions);
		}
	});
	

	}
}