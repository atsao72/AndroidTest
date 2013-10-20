package com.androidtest.austin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {
//this is a comment to test github again
	MediaPlayer ourSong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_splash);
		
		ourSong = MediaPlayer.create(Splash.this, R.raw.rooksoundclip);
		ourSong.start();
		
		Thread splashTimer = new Thread(){
			public void run(){
				try{
					
					sleep(4000);
					
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openMenu = new Intent("com.androidtest.austin.MENU");
					startActivity(openMenu);
				}
			}
		};
		
		splashTimer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}

	
}
