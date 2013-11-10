package com.androidtest.austin;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.androidtest.austin.R;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

	static Button showButton, pauseButton;
	static TextView display, levelDisplay, scoreDisplay;
	//String answer, correct;
	static String correct;
	Thread timer;
	static int level, triesLeft, score;
	int speed, levelStart;
	boolean paused;
	Object object;
	String[] questionArray;
	Bundle gotBundle;
	static ArrayList<String> questions, answers;
	Button[] levelButtons;
	static ImageView life1, life2, life3;
	static QuestionClass qClass;
	//public static boolean[] levelsLocked;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		paused=false;
		object = new MainActivity();
		levelDisplay = (TextView) findViewById(R.id.levelText);
		showButton = (Button) findViewById(R.id.buttonShow);
		//pauseButton = (Button) findViewById(R.id.pauseButton);
		display = (TextView) findViewById(R.id.textViewDisplay);
		scoreDisplay = (TextView) findViewById(R.id.scoreText);
		life1 = (ImageView) findViewById(R.id.life1);
		life2 = (ImageView) findViewById(R.id.life2);
		life3 = (ImageView) findViewById(R.id.life3);
		questions = new ArrayList<String>();
		answers = new ArrayList<String>();
		//final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		//levelsLocked = new boolean[3];
		triesLeft=3;
		//gotBundle = getIntent().getExtras();
		//levelStart = gotBundle.getInt("level");
		if(LevelSelectActivity.level==0||LevelSelectActivity.level==-1){
			levelStart=1;
		}
		else{
			levelStart= LevelSelectActivity.level;
		}
		level=levelStart;
		levelDisplay.setText("Level " + Integer.toString(level));
		scoreDisplay.setText("Score: " + Integer.toString(score));
		
		if(level<5)
			speed=4000;
		else if(level<10)
			speed=3000;
		else if(level<15)
			speed = 2000;
		else if(level<=20)
			speed=1000;
		final Animation in = new AlphaAnimation(0.0f, 1.0f);
	    in.setDuration(100);

	    final Animation out = new AlphaAnimation(1.0f, 0.0f);
	    out.setDuration(100);
	    
	    
		showButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
				if(level>Splash.MAX_LEVELS){
					finish();
					Intent finish = new Intent(MainActivity.this, FinishedActivity.class);
					startActivity(finish);					
				}
				else{
				//speed= Integer.parseInt(getPrefs.getString("speedList", "3000"));
				qClass = new QuestionClass(level);
				levelDisplay.setText("Level " + Integer.toString(level));
				showButton.setVisibility(View.INVISIBLE);
				//questionArray=questions.get(level-1).split("&");
				//display.setText(questionArray[0]);
				
				if(triesLeft==3){
					life1.setImageResource(R.drawable.image_life);
					life2.setImageResource(R.drawable.image_life);
					life3.setImageResource(R.drawable.image_life);
					getQuestion1();
				}
				if(level%5==0&&speed!=1000&&triesLeft==3){
					speed=speed-1000;
				}
				display.setText(questions.get(0));
				
				timer = new Thread() {
					public void run() {
						try {
							pauseTest();
							//if(level==26){
							//	speed=4000;
							//}
							sleep(speed);
							//for (int i = 1; i < questionArray.length; i++) {
							for (int i=1; i<3;i++){
								pauseTest();
								//speed= Integer.parseInt(getPrefs.getString("speedList", "3000"));
								if(triesLeft==3){
									getNextQuestions();
									correct=qClass.getAnswer();
								}
								final String question = questions.get(i);
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										out.setAnimationListener(new Animation.AnimationListener() {
										    @Override
										    public void onAnimationEnd(Animation animation) {
										        display.setText(question);
										        display.startAnimation(in);
										    }

											@Override
											public void onAnimationRepeat(
													Animation animation) {												
											}
											@Override
											public void onAnimationStart(
													Animation animation) {												
											}
										});
										display.startAnimation(out);
										//display.setText(question);
										//display.setText(qClass.getAdditionalEquation());
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
		save();
		pause();
	}
	
	private void save(){
		try {
			Splash.fos = openFileOutput(Splash.LEVEL, Context.MODE_PRIVATE);
			Splash.fos.flush();
			Splash.fos.write(level);
			Splash.fos = openFileOutput(Splash.SCORE, Context.MODE_PRIVATE);
			Splash.fos.write(score);
			Splash.fos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	protected void onStop() {
		super.onStop();
		save();
		finish();
	}


	public static void getQuestion1(){
		//QuestionClass qClass = new QuestionClass(level);
		questions.clear();
		questions.add(qClass.getFirstEquation());
	}
	
	public static void getNextQuestions(){
		//QuestionClass qClass = new QuestionClass(level);
		questions.add(qClass.getAdditionalEquation());
	}

}


