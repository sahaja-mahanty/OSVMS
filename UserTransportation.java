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

public class UserTransportation extends Activity  implements OnClickListener {
	
	EditText issue;
	Button viewt,viewy;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_transportation);
		
		issue=(EditText)findViewById(R.id.editText1);
		viewy=(Button)findViewById(R.id.btnadd);
		viewt=(Button)findViewById(R.id.btnview);
		viewt.setOnClickListener(this);
		viewy.setOnClickListener(this);
		try{
	          db=openOrCreateDatabase("OSVMS",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
	        }catch(Exception exception){
	            exception.printStackTrace();
	        }
		
	}
	 public void onClick(View view)
	    { 
	    	if(view==viewt)
	    	{
	    		if(issue.getText().toString().trim().length()==0)
	    		{
	    			showMessage("Error", "Please enter issue");
	    			return;
	    		}
	    		Cursor c=db.rawQuery("SELECT * FROM transport WHERE issue='"+issue.getText()+"'", null);
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
	      if(view==viewy)
	    	{
	    	
	    		Cursor c=db.rawQuery("SELECT * FROM transport", null);
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
	    	issue.requestFocus();
	    }
	}




