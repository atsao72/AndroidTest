package com.androidtest.austin;

import com.androidtest.austin.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelSelectActivity extends Activity implements View.OnClickListener{

	private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, 
	button15, button16, button17, button18, button19, button20;
	public static int level;
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
		
		buttons = new Button[]{button1,button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, 
				button15, button16, button17, button18, button19, button20};
		
		if(level==-1||level==0){
			level=1;
		}
		
		int i=0;
		while(i<level){
			buttons[i].setEnabled(true);
			i++;
		}
		/*for(int i=0;i<buttons.length;i++){
			buttons[i].setEnabled(MenuActivity.levelsLocked[i]);
		}
		
		button1.setEnabled(true);
		*/
		for(int j=0;j<buttons.length;j++){
			buttons[j].setOnClickListener(this);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.l1Button:
			level=1;
			break;
		case R.id.l2Button:
			level=2;
			break;
		case R.id.l3Button:
			level=3;
			break;
		case R.id.l4Button:
			level=4;
			break;
		case R.id.l5Button:
			level=5;
			break;
		case R.id.l6Button:
			level=6;
			break;
		case R.id.l7Button:
			level=7;
			break;
		case R.id.l8Button:
			level=8;
			break;
		case R.id.l9Button:
			level=9;
			break;
		case R.id.l10Button:
			level=10;
			break;
		case R.id.l11Button:
			level=11;
			break;
		case R.id.l12Button:
			level=12;
			break;
		case R.id.l13Button:
			level=13;
			break;
		case R.id.l14Button:
			level=14;
			break;
		case R.id.l15Button:
			level=15;
			break;
		case R.id.l16Button:
			level=16;
			break;
		case R.id.l17Button:
			level=17;
			break;
		case R.id.l18Button:
			level=18;
			break;
		case R.id.l19Button:
			level=19;
			break;
		case R.id.l20Button:
			level=20;
			break;
		}
		Intent startActivity = new Intent("com.androidtest.austin.MAINACTIVITY");
		startActivity(startActivity);
		finish();
	}
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
