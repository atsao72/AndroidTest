package com.androidtest.austin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinishedActivity extends Activity {

	TextView emailLink, message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finished);
		emailLink = (TextView)  findViewById(R.id.emailText);
		message = (TextView) findViewById(R.id.messageText);
		emailLink.setClickable(true);
		emailLink.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND); 
				emailIntent.setType("plain/text");
				String[] aEmailList = {"rook.customerservice@gmail.com"};
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
				startActivity(Intent.createChooser(emailIntent, "Send your email in:"));
			}
		});
	}


}
