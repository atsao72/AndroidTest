package com.androidtest.austin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelSelectActivity extends Activity{

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private static int level=1;
	private Button[] buttons;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_select_menu);
		button1= (Button) findViewById(R.id.l1Button);
		button2= (Button) findViewById(R.id.l2Button);
		button3= (Button) findViewById(R.id.l3Button);
		button4= (Button) findViewById(R.id.l4Button);
		buttons = new Button[]{button1, button2, button3, button4};
		
		for(int i=0;i<MenuActivity.levelsLocked.length;i++){
			buttons[i].setEnabled(MenuActivity.levelsLocked[i]);
		}
		
		button1.setEnabled(true);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=1;
				Intent startActivity = new Intent("com.androidtest.austin.STARTINGACTIVITY");
				startActivity(startActivity);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=2;
				Intent startActivity = new Intent("com.androidtest.austin.STARTINGACTIVITY");
				Bundle bundleFromLevel = new Bundle();
				bundleFromLevel.putInt("level", level);
				startActivity.putExtras(bundleFromLevel);
				startActivity(startActivity);
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=3;
				Intent startActivity = new Intent("com.androidtest.austin.STARTINGACTIVITY");
				Bundle bundleFromLevel = new Bundle();
				bundleFromLevel.putInt("level", level);
				startActivity.putExtras(bundleFromLevel);
				startActivity(startActivity);
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=4;
				Intent startActivity = new Intent("com.androidtest.austin.STARTINGACTIVITY");
				Bundle bundleFromLevel = new Bundle();
				bundleFromLevel.putInt("level", level);
				startActivity.putExtras(bundleFromLevel);
				startActivity(startActivity);
			}
		});
	}
	
	public static int getLevel(){
		return level;
	}
		
}
