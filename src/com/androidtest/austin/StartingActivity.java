package com.androidtest.austin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.text.Editable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartingActivity extends Activity {

	String equation;
	Button showButton;
	Button checkButton;
	TextView display;
	AutoCompleteTextView input;
	String answer;
	Scanner scanner;
	InputStreamReader inputStreamReader;
	BufferedReader reader;
	InputStream is;
	InputStreamReader answerInputStreamReader;
	BufferedReader answerReader;
	InputStream answerIs;
	String correct;
	Thread timer;
	Context context=this;
	Activity activity = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting);
		equation = "";
		showButton = (Button) findViewById(R.id.buttonShow);
		//checkButton = (Button) findViewById(R.id.buttonCheck);
		display = (TextView) findViewById(R.id.textViewDisplay);
		//input = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

		AssetManager am = getAssets();
		try {
			is = am.open("Math questions.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inputStreamReader = new InputStreamReader(is);
		reader = new BufferedReader(inputStreamReader);

		try {
			answerIs = am.open("answers.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		answerInputStreamReader = new InputStreamReader(answerIs);
		answerReader = new BufferedReader(answerInputStreamReader);
		
		showButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showButton.setVisibility(View.INVISIBLE);
				try {
					display.setText(reader.readLine());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				timer = new Thread() {
					public void run() {
						try {
							for (int i = 0; i < 2; i++) {
								sleep(3000);
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										try {
											display.setText(reader.readLine());
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
								});
							}
							sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{							
						activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {	
						AlertDialog.Builder alert = new AlertDialog.Builder(context);
						alert.setTitle("Your Answer");
						alert.setMessage("Input your answer:");
						final EditText inputAnswer = new EditText(context);
						alert.setView(inputAnswer);
						alert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								answer = inputAnswer.getText().toString();
								try {
									correct = answerReader.readLine();
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								String verdict = "";
								if (answer.equals(correct)) {
									verdict = "Correct!";
								} else {
									verdict = "Incorrect!";
								}
								display.setText(verdict);
					        }
						 });
						alert.show();
						showButton.setVisibility(View.VISIBLE);
						showButton.setText("Next equation");
							}
						});
						}
					}
				};
				timer.start();
				timer.stop();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.starting, menu);
		return true;
	}

}
