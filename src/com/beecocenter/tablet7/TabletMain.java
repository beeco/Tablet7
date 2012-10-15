package com.beecocenter.tablet7;

import com.beecocenter.tablet7.BagCount;
// import com.beecocenter.RegisterScreen;

import com.beecocenter.tablet7.IntentResult;
// import com.beecocenter.tablet7.RegisterScreen;

import com.beecocenter.tablet7.IntentIntegrator;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

@SuppressWarnings("unused")
public class TabletMain extends Activity {
	public final static String BEECONAME = "com.beecocenter.tablet7.BEECONAME";
	static final int SCAN_DONE = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablet_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tablet_main, menu);
        return true;
    }

    public void onScanButton(View view) {
        Activity ac = this;
        IntentIntegrator ii = new IntentIntegrator(ac);
        
        ii.initiateScan();
     }
    
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
  	  IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
  	  
  	  TextView contents = new TextView(this);
  	  String url = "hoot";
  	  CharSequence userchar = "loc=";
  	  
  	    // handle scan result
  	  if (scanResult != null) {
  		  
  	     contents.setText(scanResult.getContents()); 
  		 url = (String) contents.getText();
  		 if (url.contains(userchar)) {
  			 String[] parts = url.split(userchar.toString());
   	        
  		     Intent bagcount = new Intent(this,BagCount.class);
  	         bagcount.putExtra(BEECONAME,parts[1]);		     
  		     startActivity(bagcount);
  		  
  		 } else {
  			 
/*  				Intent registerScreen = new Intent(this,RegisterScreen.class);
  				startActivity(registerScreen);
*/  			 
  		 }
  	}

  	  
  }
	
	public void onRegisterButton(View view) {
		/*
		Intent registerScreen = new Intent(this,RegisterScreen.class);
		startActivity(registerScreen);
		*/
	}
}
