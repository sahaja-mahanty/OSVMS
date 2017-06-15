package com.ecil.osvms;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserHome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		

		Button health=(Button)findViewById(R.id.btnHealth);
		health.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(UserHome.this,UserHealthCare.class);
				startActivity(a);
				
			}
		});
		Button education=(Button)findViewById(R.id.btnEducation);
		education.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent b=new Intent(UserHome.this,UserEducation.class);
				startActivity(b);
				
			}
		});
		
		Button tr=(Button)findViewById(R.id.btnTransport);
		tr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent c=new Intent(UserHome.this,UserTransportation.class);
				startActivity(c);
				
			}
		});
		
		Button g=(Button)findViewById(R.id.btnGovt);
		g.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent d=new Intent(UserHome.this,UserGovt.class);
				startActivity(d);
				
			}
		});
	}

	

}
