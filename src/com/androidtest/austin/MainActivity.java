package com.androidtest.austin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.androidtest.austin.R;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

	static Button showButton, pauseButton;
	static TextView display, levelDisplay;
	//String answer, correct;
	static String correct; 
	InputStreamReader inputStreamReader, answerInputStreamReader;
	BufferedReader reader, answerReader;
	InputStream is, answerIs;
	Thread timer;
	static int level;
	int speed, levelStart;
	boolean paused;
	Object object;
	String[] questionArray;
	Bundle gotBundle;
	static ArrayList<String> questions, answers;
	Button[] levelButtons;
	//public static boolean[] levelsLocked;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		paused=false;
		object = new MainActivity();
		levelDisplay = (TextView) findViewById(R.id.levelText);
		showButton = (Button) findViewById(R.id.buttonShow);
		pauseButton = (Button) findViewById(R.id.pauseButton);
		display = (TextView) findViewById(R.id.textViewDisplay);
		questions = new ArrayList<String>();
		answers = new ArrayList<String>();
		//final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		speed=2000;
		//levelsLocked = new boolean[3];
		
		AssetManager am = getAssets();
		try {
			is = am.open("Math questions.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		inputStreamReader = new InputStreamReader(is);
		reader = new BufferedReader(inputStreamReader);

		try {
			answerIs = am.open("answers.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		answerInputStreamReader = new InputStreamReader(answerIs);
		answerReader = new BufferedReader(answerInputStreamReader);
		
		//gotBundle = getIntent().getExtras();
		//levelStart = gotBundle.getInt("level");
		levelStart= LevelSelectActivity.getLevel();
		level=levelStart;
		levelDisplay.setText("Level " + Integer.toString(level));
		try {
			String question=reader.readLine();
			while(question!=null){
				 questions.add(question);
				 question=reader.readLine();
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			String answer= answerReader.readLine();
			while(answer!=null){
				answers.add(answer);
				answer = answerReader.readLine();
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		
		showButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//speed= Integer.parseInt(getPrefs.getString("speedList", "3000"));
				final QuestionClass qClass = new QuestionClass(level);
				levelDisplay.setText("Level " + Integer.toString(level));
				showButton.setVisibility(View.INVISIBLE);
				//questionArray=questions.get(level-1).split("&");
				//display.setText(questionArray[0]);
				display.setText(qClass.getFirstEquation());
				
				timer = new Thread() {
					public void run() {
						try {
							pauseTest();
							if(level%5==0&&speed!=1000){
								speed=speed-1000;
							}
							if(level==26){
								speed=4000;
							}
							sleep(speed);
							//for (int i = 1; i < questionArray.length; i++) {
							for (int i=1; i<3;i++){
								pauseTest();
								//speed= Integer.parseInt(getPrefs.getString("speedList", "3000"));
								//final String question = questionArray[i];
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										//display.setText(question);
										display.setText(qClass.getAdditionalEquation());
									}
								});
							sleep(speed);
							}							
							pauseTest();
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}finally{
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								correct=qClass.getAnswer();
								Intent intent = new Intent(MainActivity.this, AnswerInputDialogActivity.class);
								//Bundle bundle = new Bundle();
								//bundle.putInt("level", level);
								//intent.putExtras(bundle);
								startActivity(intent);
								
							}
						});
						}
					}
				};
				timer.start();
			}
		});
		
//		pauseButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				pause();
//			}
//		});
	}

		
	@Override
	protected void onPause() {
		super.onPause();
		pause();
	}


	private void pauseTest(){
		synchronized(object){
			while(paused){
			//try {
				//object.wait();
				finish();
			//} //catch (InterruptedException e) {
				//e.printStackTrace();
			//}
			}
		}
	}
	
	private void pause(){
		super.onPause();
		synchronized(object){
			paused=true;
			//Intent intent = new Intent("com.mathchallenge.rook.PAUSEMENU");
			//intent.putExtra("caller", "StartingActivity");
			//startActivity(intent);
		}
	}
	
	@Override
	public void onResume(){
		super.onResume();
		synchronized(object){
			paused=false;
			object.notifyAll();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.starting, menu);
		return true;
	}
	
}


