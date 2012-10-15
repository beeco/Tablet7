package com.beecocenter.tablet7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BagCount extends Activity {
	public final static String BAGNUM = "com.beecocenter.tablet7.BAGNUM";
	public final static String BEECONAME = "com.beecocenter.tablet7.BEECONAME";
	
	String beeco = "hoot";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bagcount_screen);
		
		Intent callingi = getIntent();
		beeco = callingi.getStringExtra(BEECONAME);
	}

	public void onRestartButton(View view) {
		Intent retmain = new Intent(this, TabletMain.class);
		startActivity(retmain);
	}
	
    public void onBagButton(View view) {
    	  
    	String bagnumber = (String) view.getTag();
		Intent creditscreen = new Intent(this,DisplayCreditScreen.class);
		  
		  creditscreen.putExtra(BAGNUM, bagnumber);
		  creditscreen.putExtra(BEECONAME, beeco);
		  
		  startActivity(creditscreen); 
		  
     }
	
}
