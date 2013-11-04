package com.cs390h.triptimer;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.Menu;
import android.app.ActionBar;
/**
 * Utilizes code describe by the tutorial at:
 * http://www.androidbegin.com/tutorial/implementing-fragment-tabs-in-android/.
 * @author andrew.canastar
 *
 */

public class TripTimerActivity extends Activity {
	
	// Declare an ActionBar tab for each tab in the activity
	ActionBar.Tab mapTab, tripsTab;
	// Declare fragments for each fragment that will be placed
	// in the activity - will need to modify for multiple fragments
	// swapping in and out of the tripsTab
	Fragment mapFragment = new RouteButtonFragment();
	// Just using all trips fragment for now
	Fragment tripOptionsFragment = new TripOptionsFragment();
	TripTimerDbAdapter dbAdapter = new TripTimerDbAdapter( this );
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Activity Constructor takes a Bundle of data for when it is restarted from pause()
		super.onCreate(savedInstanceState);
		// Defines the layout of the View for the main activity
		setContentView(R.layout.activity_trip_timer);
		// getActionBar() returns reference to the activity's ActionBar
		final ActionBar actionBar = getActionBar();
		// turns on the tabs navigation mode for the action bar; shows tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// hide the ActionBar title
		actionBar.setDisplayShowTitleEnabled(false);
		// add tabs to the ActionBar and set the Fragments to pass to the TabListener method
		// TabListener just separates out the tab implementation from the main Activity
		actionBar.addTab(actionBar.newTab().
				setText("Maps").setTabListener(new TabListener(mapFragment)));
		actionBar.addTab(actionBar.newTab().
				setText("Trips").setTabListener(new TabListener(tripOptionsFragment)));
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trip_timer, menu);
		return true;
	}
	
	/**
	 * Sends a new route to the TripTimerDbAdapter's insert
	 * method.
	 * 
	 * @param r
	 */
	public void newRouteToDatabase( Route r ) {
		dbAdapter.open();
		dbAdapter.insert( r );
		dbAdapter.close();
	}
	
	/**
	 * Resets the table by calling the TripTimerDbAdapter.reCreateTable()
	 * method.
	 */
	public void resetTable() {
		dbAdapter.open();
		dbAdapter.reCreateTable();
		dbAdapter.close();
	}
}
