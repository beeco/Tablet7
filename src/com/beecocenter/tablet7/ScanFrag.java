package com.beecocenter.tablet7;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressWarnings("unused")
@TargetApi(11)
public class ScanFrag extends Fragment {
	
  
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
// Inflate the layout for this fragment
return inflater.inflate(R.layout.scan_frag, container, false);
	}
	
}
