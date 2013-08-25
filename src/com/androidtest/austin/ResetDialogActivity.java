package com.androidtest.austin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class ResetDialogActivity extends Activity	{

	Button buttonYes;
	Button buttonNo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset);
		
		buttonYes = (Button) findViewById(R.id.yesButton);
		buttonNo = (Button) findViewById(R.id.noButton);
		
	}
}
