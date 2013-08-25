package com.androidtest.austin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//Update levelSelect buttons -- change state after level passed.
//***Get rid of speed preferences

public class StartingActivity extends Activity {

	Button showButton, pauseButton;
	TextView display, levelDisplay;
	String answer, correct;
	InputStreamReader inputStreamReader, answerInputStreamReader;
	BufferedReader reader, answerReader;
	InputStream is, answerIs;
	Thread timer;
	int level, speed, levelStart;
	boolean paused;
	Object object;
	String[] questionArray;
	Bundle gotBundle;
	ArrayList<String> questions, answers;
	Button[] levelButtons;
	//public static boolean[] levelsLocked;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting);
		paused=false;		
		object = new StartingActivity();
		levelDisplay = (TextView) findViewById(R.id.levelText);
		showButton = (Button) findViewById(R.id.buttonShow);
		pauseButton = (Button) findViewById(R.id.pauseButton);
		display = (TextView) findViewById(R.id.textViewDisplay);
		questions = new ArrayList<String>();
		answers = new ArrayList<String>();
		//***Need Fix: only works if start LevelSelectActivity first.***
		//levelButtons= new Button[]{(Button) StartingActivity.myActivity.findViewById(R.id.l1Button), (Button) StartingActivity.myActivity.findViewById(R.id.l2Button), 
		//	(Button) StartingActivity.myActivity.findViewById(R.id.l3Button), (Button) StartingActivity.myActivity.findViewById(R.id.l4Button)};
		//final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		speed=4000;
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
				levelDisplay.setText("Level " + Integer.toString(level));
				showButton.setVisibility(View.INVISIBLE);
				questionArray=questions.get(level-1).split("&");
				display.setText(questionArray[0]);
				
				timer = new Thread() {
					public void run() {
						try {
							pauseTest();
							if(level%5==0&&speed!=1000){
								speed=speed-1000;
							}
							sleep(speed);
							for (int i = 1; i < questionArray.length; i++) {
								pauseTest();
								//speed= Integer.parseInt(getPrefs.getString("speedList", "3000"));
								final String question = questionArray[i];
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										display.setText(question);
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
							AlertDialog.Builder alert = new AlertDialog.Builder(StartingActivity.this);
							alert.setTitle("Your Answer");
							alert.setMessage("Input your answer:");
							final EditText inputAnswer = new EditText(StartingActivity.this);
							alert.setView(inputAnswer);
							alert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									answer = inputAnswer.getText().toString();
									try {
										correct = answers.get(level-1);
									} catch (NumberFormatException e) {
										e.printStackTrace();
									}
									String message = "";
									if (answer.equals(correct)) {
										message = "Correct!";
										showButton.setText("Next level");
										showButton.setVisibility(View.VISIBLE);
										//levelButtons[level-1].setSaveEnabled(true);
										MenuActivity.levelsLocked[level-1]=true;
										level++;
									} else {
										message = "Incorrect!";
										showButton.setText("Retry");
										showButton.setVisibility(View.VISIBLE);
									}
									display.setText(message);
								}
							});
							alert.show();
							//showButton.setText("Next level");
						}
						});
						}
					}
				};
				timer.start();
				//level++;
			}
		});
		
		pauseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pause();
			}
		});
	}
	
	private void pauseTest(){
		synchronized(object){
			while(paused){
			try {
				object.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		}
	}
	
	private void pause(){
		super.onPause();
		synchronized(object){
			paused=true;
			Intent intent = new Intent("com.androidtest.austin.PAUSEMENU");
			intent.putExtra("caller", "StartingActivity");
			startActivity(intent);
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
