package com.androidtest.austin;

import java.util.Timer;

import com.androidtest.austin.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AnswerInputDialogActivity extends Activity {

	Button buttonYes;
	EditText userInput;
	TextView time;
	Bundle gotBundle;
	Timer timer;
	ProgressBar mProgressBar;
	CountDownTimer mCountDownTimer;
	int i=100;
	private int total, counter;
	boolean soundOn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_input);
		final MediaPlayer buzzerSound = MediaPlayer.create(AnswerInputDialogActivity.this, R.raw.buzzer);
		final MediaPlayer incorrectSound = MediaPlayer.create(AnswerInputDialogActivity.this, R.raw.wrong);
		final MediaPlayer correctSound = MediaPlayer.create(AnswerInputDialogActivity.this, R.raw.bell);

		buttonYes = (Button) findViewById(R.id.okButton);
		userInput = (EditText) findViewById(R.id.input);

		userInput.setRawInputType(Configuration.KEYBOARD_12KEY);
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		soundOn = getPrefs.getBoolean("sound", true);
		
		mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
		mProgressBar.setProgress(i);
		mCountDownTimer=new CountDownTimer(10000,100) {
		    @Override
		    public void onTick(long millisUntilFinished) {
		        i--;
		        mProgressBar.setProgress(i);
		    }
		    @Override
		    public void onFinish() {
		      	i--;
		        mProgressBar.setProgress(i);
		        finish();
		        if(soundOn)
		        	buzzerSound.start();	
				String message = "Too slow!";
				MainActivity.triesLeft--;
				switch(MainActivity.triesLeft){
				case 2:
					MainActivity.life1.setImageResource(R.drawable.minus_sign);
					MainActivity.showButton.setText("Retry");
					break;
				case 1:
					MainActivity.life2.setImageResource(R.drawable.minus_sign);
					MainActivity.showButton.setText("Retry");
					break;
				case 0:
					MainActivity.life3.setImageResource(R.drawable.minus_sign);
					MainActivity.showButton.setText("New Equation");
					MainActivity.triesLeft=3;
					break;
				}
				//MainActivity.showButton.setText("Retry");
				MainActivity.showButton.setVisibility(View.VISIBLE);
				MainActivity.display.setText(message);
		    }
		};
		mCountDownTimer.start();
		
		
		buttonYes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final int start = MainActivity.score;
				int k=0;
				switch(MainActivity.triesLeft){
				case 3:
					k=15;
					break;
				case 2:
					k=10;
					break;
				case 1:
					k=5;
					break;
				}
				total=0; 
				counter = start;
				mCountDownTimer.cancel();
				//String correct = "";
				String answer = userInput.getText().toString();
				try {
					//correct = MainActivity.answers.get(MainActivity.level - 1);
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				String message = "";
				if (answer.equals(MainActivity.correct)) {
					MainActivity.score+=((i/10)+2+k);
					if(MainActivity.score<0){
						MainActivity.score=0;
					}
					if(soundOn)
						correctSound.start();
					total=MainActivity.score;
					message = "Correct!";
					MainActivity.triesLeft=3;
					MainActivity.showButton.setText("Next level");
					MainActivity.showButton.setVisibility(View.VISIBLE);
					//MenuActivity.levelsLocked[MainActivity.level - 1] = true;
					MainActivity.level++;
					LevelSelectActivity.level++;
				} else {
					if(soundOn)
						incorrectSound.start();
					message = "Incorrect!";
					MainActivity.triesLeft--;
					MainActivity.score-=((3-MainActivity.triesLeft)*5);
					if(MainActivity.score<0){
						MainActivity.score=0;
					}
					total=MainActivity.score;
					switch(MainActivity.triesLeft){
					case 2:
						MainActivity.life1.setImageResource(R.drawable.minus_sign);
						MainActivity.showButton.setText("Retry");
						break;
					case 1:
						MainActivity.life2.setImageResource(R.drawable.minus_sign);
						MainActivity.showButton.setText("Retry");
						break;
					case 0:
						MainActivity.life3.setImageResource(R.drawable.minus_sign);
						MainActivity.showButton.setText("New Equation");
						MainActivity.triesLeft=3;
						break;
					}
					MainActivity.showButton.setVisibility(View.VISIBLE);
				}
				MainActivity.display.setText(message);
			
			    new Thread(new Runnable() {
		            public void run() {
		            	if(counter<total){
		            		while (counter < total) {
		            			try {
		            				Thread.sleep(50);
		            			} catch (InterruptedException e) {
		            				e.printStackTrace();
		            			}
		            			MainActivity.scoreDisplay.post(new Runnable() {
		            				public void run() {
		            					MainActivity.scoreDisplay.setText("Score: " + Integer.toString(counter));
		            				}
		            			});
		            			counter++;
		            		}
		            	}
		            	else{
		            		while (counter > total) {
		            			try {
		            				Thread.sleep(50);
		            			} catch (InterruptedException e) {
		            				e.printStackTrace();
		            			}
		            			MainActivity.scoreDisplay.post(new Runnable() {
		            				public void run() {
		            					MainActivity.scoreDisplay.setText("Score: " + Integer.toString(counter));
		            				}
		            			});
		            			counter--;
		            		}
		            	}
		            }
		        }).start();
				//MainActivity.scoreDisplay.setText("Score: " + Integer.toString(MainActivity.score));
				finish();
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mCountDownTimer.cancel();
		finish();
		//MainActivity.showButton.setText("Retry");
		//MainActivity.showButton.setVisibility(View.VISIBLE);
	}
	
	

}
