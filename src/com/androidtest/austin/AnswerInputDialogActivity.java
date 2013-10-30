package com.androidtest.austin;

import java.util.Timer;
import java.util.TimerTask;

import com.androidtest.austin.R;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_input);

		buttonYes = (Button) findViewById(R.id.okButton);
		userInput = (EditText) findViewById(R.id.input);

		userInput.setRawInputType(Configuration.KEYBOARD_12KEY);
		
		// gotBundle = getIntent().getExtras();
		// final int level = gotBundle.getInt("level");
		
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
					message = "Correct!";
					MainActivity.triesLeft=3;
					MainActivity.showButton.setText("Next level");
					MainActivity.showButton.setVisibility(View.VISIBLE);
					//MenuActivity.levelsLocked[MainActivity.level - 1] = true;
					MainActivity.level++;
					LevelSelectActivity.level++;
				} else {
					message = "Incorrect!";
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
					MainActivity.showButton.setVisibility(View.VISIBLE);
				}
				MainActivity.display.setText(message);
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
