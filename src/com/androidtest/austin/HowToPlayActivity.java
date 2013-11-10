package com.androidtest.austin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HowToPlayActivity extends Activity {

	static Button nextButton;
	static TextView instructions;
	
	int numClicks;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.how_to_play_1);
		
		nextButton = (Button) findViewById(R.id.nextButton);
		instructions = (TextView) findViewById(R.id.instructions);
		numClicks = 0; 
		
		nextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch(numClicks){
				case 0: 
					instructions.setText("After 4 seconds, the initial equation will change to say something like: \n Add 6 \n Keep the new answer, 13, in mind.");
					numClicks++;
					break;
				case 1:
					instructions.setText("In another 4 seconds, the equation will change again to a different equation, such as: \n Subtract 11 \n Keep the answer, 2, in mind.");
					numClicks++;
					break;
				case 2: 
					instructions.setText("When prompted, input the correct answer before the time runs out! If it is correct, you advance to the next level. If you are incorrect or the time runs out, you will be given 2 more chances before the equations change. ");
					numClicks++;
					break;
				case 3:
					instructions.setText("The time between equations will start to decrease as you advance. \n Your score is calculated based on speed and how many lives you have. \n Good luck!");
					numClicks++;
					break;
				case 4:
					finish();
					break;
				}
			}			
			});
		}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	
	
}
