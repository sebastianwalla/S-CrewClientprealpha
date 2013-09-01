package com.example.s_crewclientprealpha;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AdminConsoleActivity extends Activity {
	Socket serverside = null;
	   InputStream i=null;
	   OutputStream o=null;
	   ObjectOutputStream oos = null;
	   ObjectInputStream ois=null;
	   Thread t;
	   Thread t1;
	   Object GesamtBestellung;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_console);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
 }
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	public void setRestaurant(View view){
		t=new Thread(new Runnable(){
			public void run(){
				try{
			 		serverside=new Socket("veteran1.ez.lv",5544);//serverside ist der server
		 			i= serverside.getInputStream();
					o= serverside.getOutputStream();
					oos = new ObjectOutputStream(o);
					ois= new ObjectInputStream(i); 
				    oos.writeObject("setRestaurant()");
				    EditText et= (EditText) findViewById(R.id.txtfieldrestaurant);
				    String Restaurant;
				    Restaurant=et.getText().toString();
				    oos.writeObject(Restaurant);
			     } catch (UnknownHostException e) {
			    	 e.printStackTrace();
				
				// TODO Auto-generated catch block
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			}
			}	
			
		});
		t.start();	
	
     }
	public void getGesamt(View view){
		t1=new Thread(new Runnable(){
			public void run(){
				try{
			 		serverside=new Socket("veteran1.ez.lv",5544);//serverside ist der server
		 			i= serverside.getInputStream();
					o= serverside.getOutputStream();
					oos = new ObjectOutputStream(o);
					ois= new ObjectInputStream(i); 
				    oos.writeObject("getGesamt()");
				    GesamtBestellung=ois.readObject();
					
			     } catch (UnknownHostException e) {
			    	 e.printStackTrace();
				
				// TODO Auto-generated catch block
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}	
			
		});
		t1.start();	
	
     }
	
}
