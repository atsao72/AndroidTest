package com.androidtest.austin;

import java.util.ArrayList;

import com.androidtest.austin.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LevelSelectActivity extends Activity{

	//private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, 
	//button15, button16, button17, button18, button19, button20;
	public static int level;
	private ArrayList<Button> buttons;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_select_menu);
		TextView levelSet = (TextView) findViewById(R.id.levelSetText);
		Button buttonNext = (Button) findViewById(R.id.bNextSet);
		/*button1 = (Button) findViewById(R.id.l1Button); button2 = (Button) findViewById(R.id.l2Button); button3 = (Button) findViewById(R.id.l3Button);
		button4 = (Button) findViewById(R.id.l4Button); button5 = (Button) findViewById(R.id.l5Button); button6 = (Button) findViewById(R.id.l6Button);
		button7 = (Button) findViewById(R.id.l7Button); button8 = (Button) findViewById(R.id.l8Button); button9 = (Button) findViewById(R.id.l9Button);
		button10 = (Button) findViewById(R.id.l10Button); button11 = (Button) findViewById(R.id.l11Button); button12 = (Button) findViewById(R.id.l12Button);
		button13 = (Button) findViewById(R.id.l13Button); button14 = (Button) findViewById(R.id.l14Button); button15 = (Button) findViewById(R.id.l15Button);
		button16 = (Button) findViewById(R.id.l16Button); button17 = (Button) findViewById(R.id.l17Button); button18 = (Button) findViewById(R.id.l18Button);
		button19 = (Button) findViewById(R.id.l19Button); button20 = (Button) findViewById(R.id.l20Button);
		buttons = new Button[]{button1,button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, 
				button15, button16, button17, button18, button19, button20};
		*/
		//LinearLayout layout = (LinearLayout) findViewById(R.layout.level_select_menu);
		
		buttons = new ArrayList<Button>();
		if(level<=20)
			levelSet.setText("Set 1");
		LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
		LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
		LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout3);
		LinearLayout layout4 = (LinearLayout) findViewById(R.id.layout4);
		LinearLayout layout5 = (LinearLayout) findViewById(R.id.layout5);
		for(int i=0;i<Splash.MAX_LEVELS;i++){
	        final Button btn = new Button(this);
	        final int j = i;
	        buttons.add(btn);
	        btn.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                level = j+1;
	                Intent intent = new Intent(LevelSelectActivity.this, MainActivity.class);
	                startActivity(intent);
	                finish();
	            }
	        });
	        btn.setBackgroundResource(R.drawable.custom);
	        btn.setText(Integer.toString(i+1));
	        btn.setEnabled(false);
	        btn.setTextSize(20);
	        btn.setTypeface(null, Typeface.BOLD);
	        LayoutParams buttonLayoutParams = new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT,1f);
	        buttonLayoutParams.setMargins(0, 0, 20, 0);
	        if(i==0 || i==4 || i==8 || i==12|| i==16)
	        	buttonLayoutParams.setMargins(20, 0, 20, 0);
	        btn.setLayoutParams(buttonLayoutParams);
	        if(i<4)
	        	layout1.addView(btn);
	        else if(i<8)
	        	layout2.addView(btn);
	        else if(i<12)
	        	layout3.addView(btn);
	        else if(i<16)
	        	layout4.addView(btn);
	        else if(i<20)
	        	layout5.addView(btn);
		}
		
		if(level==-1||level==0){
			level=1;
		}
		int i=0;
		int limit;
		if(level>buttons.size()){
			limit=buttons.size();
		}
		else{
			limit=level;
		}
		while(i<limit){
			buttons.get(i).setEnabled(true);
			i++;
		}

		/*buttonNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent = new Intent
			}
		});*/
		
	}
	/*
	@Override
	public void onClick(View v) {
		
	}
	*/
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}

