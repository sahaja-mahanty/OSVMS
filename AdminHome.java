package com.ecil.osvms;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AdminHome extends Activity implements OnClickListener {
	
	EditText issue,solution;
	Button viewu,deleteu,viewv,deletev;
	SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_home);
		issue=(EditText)findViewById(R.id.editText1);
		solution=(EditText)findViewById(R.id.editText2);
		viewu=(Button)findViewById(R.id.buttonviewu);
		deleteu=(Button)findViewById(R.id.buttondelu);
		viewv=(Button)findViewById(R.id.buttonviewv);
		deletev=(Button)findViewById(R.id.buttondelv);
		viewu.setOnClickListener(this);
		deleteu.setOnClickListener(this);
		viewv.setOnClickListener(this);
		deletev.setOnClickListener(this);
		try{
	          db=openOrCreateDatabase("OSVMS",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
	        }catch(Exception exception){
	            exception.printStackTrace();
	        }
		
	}
	 public void onClick(View view)
	    {
	    	
	    	
	    	
	    	if(view==deleteu)
	    	{
	    		if(issue.getText().toString().trim().length()==0)
	    		{
	    			showMessage("Error", "Please enter user");
	    			return;
	    		}
	    		Cursor c=db.rawQuery("SELECT * FROM user WHERE id='"+issue.getText()+"'", null);
	    		if(c.moveToFirst())
	    		{
	    			db.execSQL("DELETE FROM user WHERE id='"+issue.getText()+"'");
	    			showMessage("Success", "Record Deleted");
	    		}
	    		else
	    		{
	    			showMessage("Error", "Invalid User");
	    		}
	    		clearTextU();
	    	}
	    	if(view==viewu)
	    	{
	    	
	    		Cursor c=db.rawQuery("SELECT * FROM user", null);
	    		if(c.getCount()==0)
	    		{
	    			showMessage("Error", "No records found");
	    			return;
	    		}
	    		StringBuffer buffer=new StringBuffer();
	    		while(c.moveToNext())
	    		{
	    			buffer.append("User: "+c.getString(0)+"\n\n");
	    			
	    		}
	    		showMessage("Requested Details", buffer.toString());
	    	}
	    	if(view==deletev)
	    	{
	    		if(solution.getText().toString().trim().length()==0)
	    		{
	    			showMessage("Error", "Please enter volunteer");
	    			return;
	    		}
	    		Cursor c=db.rawQuery("SELECT * FROM vale WHERE id='"+solution.getText()+"'", null);
	    		if(c.moveToFirst())
	    		{
	    			db.execSQL("DELETE FROM vale WHERE id='"+solution.getText()+"'");
	    			showMessage("Success", "Record Deleted");
	    		}
	    		else
	    		{
	    			showMessage("Error", "Invalid Volunteer ID");
	    		}
	    		clearTextV();
	    	}
	    	if(view==viewv)
	    	{
	    	
	    		Cursor c=db.rawQuery("SELECT * FROM vale", null);
	    		if(c.getCount()==0)
	    		{
	    			showMessage("Error", "No records found");
	    			return;
	    		}
	    		StringBuffer buffer=new StringBuffer();
	    		while(c.moveToNext())
	    		{
	    			buffer.append("Volunteer: "+c.getString(0)+"\n\n");
	    			
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
	    public void clearTextU()
	    {
	    	issue.setText("");
	    	issue.requestFocus();
	    }
	    public void clearTextV()
	    {
	    	solution.setText("");
	    	solution.requestFocus();
	    }

	

}
