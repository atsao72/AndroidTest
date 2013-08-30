package com.mathchallenge.rook;

import java.util.Timer;
import java.util.TimerTask;

import com.mathchallenge.rook.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
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
	int i=500;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_input);

		buttonYes = (Button) findViewById(R.id.okButton);
		userInput = (EditText) findViewById(R.id.input);

		// gotBundle = getIntent().getExtras();
		// final int level = gotBundle.getInt("level");

		/*timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				AnswerInputDialogActivity.this.finish();
			}
		}, 5000);
*//*
		Handler handler = new Handler();
	    Runnable r=new Runnable() {
	    	@Override
	        public void run() {
	    		finish();
	        }         
	    };
	    handler.postDelayed(r, 5000); 
	     
		Thread thread = new Thread() {
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						for (int i = 5; i > 0; i--) {
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							time.setText(Integer.toString(i));
						}
						
					}
				});
			}
		};
		thread.start();
 */  
		
		mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
		mProgressBar.setProgress(i);
		mCountDownTimer=new CountDownTimer(5000,8) {
		    @Override
		    public void onTick(long millisUntilFinished) {
		        i=i-6;
		        mProgressBar.setProgress(i);
		    }
		    @Override
		    public void onFinish() {
		      	i=i-6;
		        mProgressBar.setProgress(i);
		        finish();
				String message = "Incorrect!";
				MainActivity.showButton.setText("Retry");
				MainActivity.showButton.setVisibility(View.VISIBLE);
				MainActivity.display.setText(message);
		    }
		};
		mCountDownTimer.start();
		    
		
		buttonYes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCountDownTimer.cancel();
				String correct = "";
				String answer = userInput.getText().toString();
				try {
					correct = MainActivity.answers.get(MainActivity.level - 1);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				String message = "";
				if (answer.equals(correct)) {
					message = "Correct!";
					MainActivity.showButton.setText("Next level");
					MainActivity.showButton.setVisibility(View.VISIBLE);
					// levelButtons[level-1].setSaveEnabled(true);
					MenuActivity.levelsLocked[MainActivity.level - 1] = true;
					MainActivity.level++;
				} else {
					message = "Incorrect!";
					MainActivity.showButton.setText("Retry");
					MainActivity.showButton.setVisibility(View.VISIBLE);
				}
				MainActivity.display.setText(message);
				finish();
			}
		});

	}
}
