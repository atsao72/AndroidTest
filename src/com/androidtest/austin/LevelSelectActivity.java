package com.androidtest.austin;

import com.androidtest.austin.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelSelectActivity extends Activity{

	private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, 
	button15, button16, button17, button18, button19, button20;
	public static int level=1;
	private Button[] buttons;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_select_menu);
		button1 = (Button) findViewById(R.id.l1Button); button2 = (Button) findViewById(R.id.l2Button); button3 = (Button) findViewById(R.id.l3Button);
		button4 = (Button) findViewById(R.id.l4Button); button5 = (Button) findViewById(R.id.l5Button); button6 = (Button) findViewById(R.id.l6Button);
		button7 = (Button) findViewById(R.id.l7Button); button8 = (Button) findViewById(R.id.l8Button); button9 = (Button) findViewById(R.id.l9Button);
		button10 = (Button) findViewById(R.id.l10Button); button11 = (Button) findViewById(R.id.l11Button); button12 = (Button) findViewById(R.id.l12Button);
		button13 = (Button) findViewById(R.id.l13Button); button14 = (Button) findViewById(R.id.l14Button); button15 = (Button) findViewById(R.id.l15Button);
		button16 = (Button) findViewById(R.id.l16Button); button17 = (Button) findViewById(R.id.l17Button); button18 = (Button) findViewById(R.id.l18Button);
		button19 = (Button) findViewById(R.id.l19Button); button20 = (Button) findViewById(R.id.l20Button);
		
		buttons = new Button[]{button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, 
				button15, button16, button17, button18, button19, button20};
		
		for(int i=0;i<buttons.length;i++){
			buttons[i].setEnabled(MenuActivity.levelsLocked[i]);
		}
		
		button1.setEnabled(true);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=1;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=2;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=3;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=4;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=5;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=6;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=7;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=8;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=9;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=10;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=11;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=12;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=13;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=14;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=15;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=16;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=17;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=18;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button19.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=19;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
		button20.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level=20;
				Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
				startActivity(startActivity);
				finish();
			}
		});
		
	}
	
	public static int getLevel(){
		return level;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
		
}
