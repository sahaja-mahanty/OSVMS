package com.ecil.osvms;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ValHome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_val_home);
		
		Button health=(Button)findViewById(R.id.btnHealth);
		health.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(ValHome.this,VolunteerHealthCare.class);
				startActivity(a);
				
			}
		});
		Button education=(Button)findViewById(R.id.btnEducation);
		education.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent b=new Intent(ValHome.this,VolunteerEducation.class);
				startActivity(b);
				
			}
		});
		
		Button tr=(Button)findViewById(R.id.btnTransport);
		tr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent c=new Intent(ValHome.this,VolunteerTransportation.class);
				startActivity(c);
				
			}
		});
		
		Button g=(Button)findViewById(R.id.btnGovt);
		g.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent d=new Intent(ValHome.this,VolunteerGovt.class);
				startActivity(d);
				
			}
		});
	}


}
