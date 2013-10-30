package com.androidtest.austin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

public class Splash extends Activity {

	//MediaPlayer ourSong;
	Thread splashTimer;
	public static FileOutputStream fos;
	public static String FILENAME="Level";
	public static final int MAX_LEVELS = 20;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		/*try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			//fos.write(1);
			fos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
		new loadData().execute(FILENAME);
		
		//ourSong = MediaPlayer.create(Splash.this, R.raw.rooksoundclip);
		//ourSong.start();
		
		splashTimer = new Thread(){
			public void run(){
				try{
					sleep(3000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openMenu = new Intent("com.androidtest.austin.MENU");
					openMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
					openMenu.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(openMenu);
				}
			}
		};
		
		splashTimer.start();
	}
/*
	@Override
	protected void onStart() {
		super.onStart();
		setContentView(R.layout.activity_splash);
		try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(1);
			fos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//ourSong = MediaPlayer.create(Splash.this, R.raw.rooksoundclip);
		//ourSong.start();
		
		splashTimer = new Thread(){
			public void run(){
				try{
					sleep(3000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					new loadData().execute(FILENAME);
					Intent openMenu = new Intent("com.androidtest.austin.MENU");
					openMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
					openMenu.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(openMenu);
				}
			}
		};
		splashTimer.start();
	}
*/
	@Override
	protected void onStop() {
		super.onStop();
		splashTimer.interrupt();
		finish();
	}

	public class loadData extends AsyncTask<String, Integer, String>{

		@Override
		protected String doInBackground(String... arg0) {
			try {
				FileInputStream fis = openFileInput(FILENAME);
				LevelSelectActivity.level=fis.read();
				fis.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
}
