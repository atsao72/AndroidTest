package com.androidtest.austin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelSelectActivity extends Activity{

	Button button1;
	Button button2;
	Button button3;
	Button button4;
	int level=1;
	
	public LevelSelectActivity(){
	
	}
	public void setActivity(){
		StartingActivity.myActivity=this;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_select_menu);
		button1= (Button) findViewById(R.id.l1Button);
		button2= (Button) findViewById(R.id.l2Button);
		button3= (Button) findViewById(R.id.l3Button);
		button4= (Button) findViewById(R.id.l4Button);
		StartingActivity.myActivity= this;

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
	
	public int getLevel(){
		return level;
	}
		
}
