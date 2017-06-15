package com.ecil.osvms;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{
	Button user,val,log;
	EditText id,password;
	SQLiteDatabase db;
	String u,p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		id=(EditText)findViewById(R.id.txtid);
		password=(EditText)findViewById(R.id.txtpass);
		
		user=(Button)findViewById(R.id.btnureg);
		user.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(MainActivity.this,UserReg.class);
				startActivity(a);
				
			}
		});
		
		val=(Button)findViewById(R.id.btnvreg);
		val.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent av=new Intent(MainActivity.this,ValReg.class);
				startActivity(av);
				
			}
		});
		
		log=(Button)findViewById(R.id.btnsignin);
		log.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(id.getText().toString().equals("")||password.getText().toString().equals("")){
					
					Toast.makeText(MainActivity.this, "PLz enter the fields..!", Toast.LENGTH_LONG).show();
				}else{	 
					 u = id.getText().toString();
					 p = password.getText().toString();
					 try{
					          db=openOrCreateDatabase("OSVMS",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
					        }catch(Exception exception){
					            exception.printStackTrace();
					        }try{
					        	 Cursor cc = db.rawQuery("select * from user where id = '"+u+"' and password = '"+p+"' ", null);
					        	 Cursor cc1 = db.rawQuery("select * from vale where id = '"+u+"' and password = '"+p+"' ", null);
					        	 if(id.getText().toString().equals("Admin")&& password.getText().toString().equals("admin")){
					        		 Toast.makeText(MainActivity.this, "Welcome To Admin Home Page "  + u , Toast.LENGTH_LONG).show();
					            		Intent i = new Intent(MainActivity.this,AdminHome.class);
					            		startActivity(i);
									}
					        	 // User Login
					        	 if(cc.moveToFirst())
					        		 {String temp="";					       
						            if (cc != null) {
						            	if(cc.getCount() > 0)
						            	{
						            	//return true;
						            scan g=new scan();
						            g.execute();
						            
						            		Toast.makeText(MainActivity.this, "Welcome To User Home Page "  + u , Toast.LENGTH_LONG).show();
					            		Intent i = new Intent(MainActivity.this,UserHome.class);
						            		startActivity(i);
						            	}else{
						            		 Toast.makeText(MainActivity.this, "Login Fails..!", Toast.LENGTH_LONG).show();
						            	}
						            	}
					        		 }
					        	// Valenteer Login
					        	 else if(cc1.moveToFirst())
					        	 {
					        		 String temp="";					       
							            if (cc1 != null) {
							            	if(cc1.getCount() > 0)
							            	{
							            	//return true;
							            scan g=new scan();
							            g.execute();
							            		Toast.makeText(MainActivity.this, "Welcome To VALENTEE Home Page "  + u , Toast.LENGTH_LONG).show();
							            		//globalvariabel.Setusername(user.getText().toString().trim());
							    				//globalvariabel.Setpassword(pass.getText().toString().trim());
							            		
						            		Intent i = new Intent(MainActivity.this,ValHome.class);
							            		startActivity(i);
							            	}else
							            	{
							            		 Toast.makeText(MainActivity.this, "Login Fails..!", Toast.LENGTH_LONG).show();
							            	}
							            	}
						        		 }
						            //	return false;
					        }catch(Exception exception){
					            exception.printStackTrace();
					        }
						}	 	
				}
		});
		
	
		
		
	}
	public class scan extends AsyncTask<String, String, String>{

		private ProgressDialog pd;

		protected void onPreExecute() {
			super.onPreExecute();
		 pd = new ProgressDialog(MainActivity.this);
		 pd.setTitle("Please Wait");
		 pd.setMessage("Logging....");
		 pd.setMax(200);
		 pd.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	

	

}
