package com.beecocenter.tablet7;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DisplayCreditScreen extends Activity {
	public final static String BAGNUM = "com.beecocenter.tablet7.BAGNUM";
	public final static String BEECONAME = "com.beecocenter.tablet7.BEECONAME";
//	public final static String TEST_EMAIL = "leslielx@yahoo.com";
	public final static String TEST_EMAIL = "daniel.the.moth@gmail.com";
	public final static String EMAIL_SUBJECT = "BEECO SMART REWARDS CREDIT";
	public final static String FROM_EMAIL = "beecokiosk@gmail.com";
	public final static String TO_FIELD = "beeco.kiosk.TO_FIELD";
	public final static String FROM_FIELD = "beeco.kiosk.FROM_FIELD";
	public final static String SUBJECT_FIELD = "beeco.kiosk.SUBJECT_FIELD";
	public final static String MESSAGE_FIELD = "beeco.kiosk.MESSAGE_FIELD";
	public final static String ACCOUNT_PWORD = "k1osk$$$";
	public final static String ACCOUNT_NAME = "beecokiosk@gmail.com";
	public final static String BEECO_URL = "http://kiosk.beeco.us/dbinsert.php";
	public final static String CHECK_BB = "https://beecocenter.com/mtest/checkit.php?kioskuser=";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.credit_screen);
		
		Intent i = getIntent();
			String bagnum = i.getStringExtra(BAGNUM);
			String name = i.getStringExtra(BEECONAME);

		String baglab = "BAGS";
		if (bagnum.equals("1")) { baglab = "BAG"; }
		String message = "YOU EARNED REWARDS FOR USING " + bagnum + " " + baglab + " TODAY!";
		
        String vendor_id="8";
        DataBaseQuery dbq = new DataBaseQuery();
        dbq.execute(ACCOUNT_NAME, ACCOUNT_PWORD, TEST_EMAIL, FROM_EMAIL, EMAIL_SUBJECT, message, name, bagnum, vendor_id );
        
		TextView thankView = (TextView) findViewById(R.id.thankyou);
		thankView.setText("THANK YOU, " + name);
	
		TextView earnView = (TextView) findViewById(R.id.earned);
		earnView.setText(message);


	}

	public void onRestartButton(View view) {
		Intent retmain = new Intent(this, TabletMain.class);
		startActivity(retmain);
	}
	
	public void onCheckButton(View view) {
		String checkit = CHECK_BB + "Danny";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(checkit));
        startActivity(i);
	}
	
	private class DataBaseQuery extends AsyncTask<String,Void,String> {

		@Override
		protected String doInBackground(String... params) {
						
		String urlstr = BEECO_URL + "/?beeconame=" + params[6] + "&bagnum=" + params[7] + "&vendor_id=" + params[8];
		System.out.println("before download");
		InputStream is = download(urlstr);
		System.out.println("outta download");	
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        mailtask( params[0],params[1],params[2],params[3],params[4],params[5] ); 
		System.out.println("outta mailer");	
		
		return null;
	}
					
	public InputStream download(String url) {
		URL myFileURL = null;
		InputStream is = null;
					    
		try {
			myFileURL = new URL(url);
			is = myFileURL.openStream();
			System.out.println(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return is;
	}				

	public void mailtask(String a0,String a1,String a2,String a3,String a4,String a5) {
		
	       Mail m = new Mail(a0,a1);
	       
	        String[] toArr = { a2 }; 
	        m.setTo(toArr); 
	        m.setFrom( a3 ); 
	        m.setSubject(a4); 
	        m.setBody(a5); 
	        
	        try { 
	        	
	            if(m.send()) { 
	          //  	Toast.makeText(getApplicationContext(), "Email was sent successfully.", Toast.LENGTH_LONG).show(); 
	         //   	Log.i("hoot","hoot hoot");
	            } else { 
	         //   	Log.i("no hoot", "no hooting");
	           // 	Toast.makeText(getApplicationContext(), "Email was not sent.", Toast.LENGTH_LONG).show(); 
	            } 
	          } catch(Exception e) { 
	         //   Toast.makeText(getApplicationContext(), "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
	            Log.e("MailApp", "Could not send email", e); 
	          } 
	    	}
}	
	
}
