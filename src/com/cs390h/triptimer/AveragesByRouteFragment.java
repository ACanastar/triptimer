package com.cs390h.triptimer;

import android.app.Fragment;
import android.os.Bundle;

public class AveragesByRouteFragment extends Fragment {
	
	static AveragesByRouteFragment newInstance( String title ) {
		AveragesByRouteFragment fragment = new AveragesByRouteFragment();
		return fragment;
	}
	
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
	}
}
