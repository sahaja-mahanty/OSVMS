package com.ecil.osvms;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserReg extends Activity {
	EditText id,pas,mob,email;
	Button sub;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_reg);
		id=(EditText)findViewById(R.id.editText1);
		pas=(EditText)findViewById(R.id.editText2);
		mob=(EditText)findViewById(R.id.editText3);
		email=(EditText)findViewById(R.id.editText4);
		sub=(Button)findViewById(R.id.button1);
		db=openOrCreateDatabase("OSVMS", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists user(id varchar,password varchar,mobile varchar,email varchar);");
		sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(id.getText().toString().trim().length()==0||
						pas.getText().toString().trim().length()==0||
						mob.getText().toString().trim().length()==0||
						email.getText().toString().trim().length()==0)
				{
					Toast.makeText(UserReg.this, "Enter all the values", Toast.LENGTH_SHORT).show();
					return;
				}
				else if (mob.getText().toString().trim().length()!=10)
				{
					Toast.makeText(UserReg.this, "Enter 10 digit Mobile No", Toast.LENGTH_SHORT).show();
					return;
				}
				Cursor c=db.rawQuery("select * from user where id='"+id.getText()+"'", null);
						if(c.moveToFirst())
						{
							Toast.makeText(UserReg.this, "User Aleady existed", Toast.LENGTH_SHORT).show();
							return;
						}
						else
						{
						db.execSQL("insert into user values('"+id.getText()+"','"+pas.getText()+"','"+mob.getText()+"','"+email.getText()+"');");
						Toast.makeText(UserReg.this, "Registration done", Toast.LENGTH_SHORT).show();
						ecil();
						
						}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_reg, menu);
		return true;
	}
	public void ecil()
	{
	id.setText("");
	pas.setText("");
	mob.setText("");
	email.setText("");
	}

}
