package com.ecil.osvms;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class VolunteerHealthCare extends Activity  implements OnClickListener {
	
	EditText issue,solution;
	Button add,viewy,delete;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volunteer_health_care);
		
		issue=(EditText)findViewById(R.id.editText1);
		solution=(EditText)findViewById(R.id.editText2);
		add=(Button)findViewById(R.id.buttonadd);
		viewy=(Button)findViewById(R.id.buttonview);
		delete=(Button)findViewById(R.id.buttondel);
		add.setOnClickListener(this);
		viewy.setOnClickListener(this);
		delete.setOnClickListener(this);
		db=openOrCreateDatabase("OSVMS", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists health(issue varchar,solution varchar);");	
	}
	 public void onClick(View view)
	    {
	    	
	    	if(view==add)
	    	{
	    		
	    		if(issue.getText().toString().trim().length()==0||
	    		   solution.getText().toString().trim().length()==0)
	    		{
	    			showMessage("Error", "Please enter all values");
	    			return;
	    		}
	    		db.execSQL("INSERT INTO health VALUES('"+issue.getText()+"','"+solution.getText()+"');");
	    		showMessage("Success", "Record added");
	    		clearText();
	    	}
	    	
	    	
	    	if(view==delete)
	    	{
	    		if(issue.getText().toString().trim().length()==0)
	    		{
	    			showMessage("Error", "Please enter issue");
	    			return;
	    		}
	    		Cursor c=db.rawQuery("SELECT * FROM health WHERE issue='"+issue.getText()+"'", null);
	    		if(c.moveToFirst())
	    		{
	    			db.execSQL("DELETE FROM health WHERE issue='"+issue.getText()+"'");
	    			showMessage("Success", "Record Deleted");
	    		}
	    		else
	    		{
	    			showMessage("Error", "Invalid Issue");
	    		}
	    		clearText();
	    	}
	    	if(view==viewy)
	    	{
	    	
	    		Cursor c=db.rawQuery("SELECT * FROM health", null);
	    		if(c.getCount()==0)
	    		{
	    			showMessage("Error", "No records found");
	    			return;
	    		}
	    		StringBuffer buffer=new StringBuffer();
	    		while(c.moveToNext())
	    		{
	    			buffer.append("Issue: "+c.getString(0)+"\n");
	    			buffer.append("Solution: "+c.getString(1)+"\n\n");
	    		}
	    		showMessage("Requested Details", buffer.toString());
	    	}
	    
	    }
	    public void showMessage(String title,String message)
	    {
	    	Builder builder=new Builder(this);
	    	builder.setCancelable(true);
	    	builder.setTitle(title);
	    	builder.setMessage(message);
	    	builder.show();
		}
	    public void clearText()
	    {
	    	issue.setText("");
	    	solution.setText("");
	    	issue.requestFocus();
	    }
	}


