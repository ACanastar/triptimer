package com.cs390h.triptimer;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TripSortActivity extends Activity {
	private DialogFragment dialogFragment;
	
	
	// Just using all trips fragment for now
	Fragment allTripsFragment = new AllTripsListFragment();
	TripTimerDbAdapter dbAdapter = new TripTimerDbAdapter( this );
	List<Route> trips;
	//FragmentTransaction ft = getFragmentManager().beginTransaction();
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Activity Constructor takes a Bundle of data for when it is restarted from pause()
		super.onCreate(savedInstanceState);
		// Defines the layout of the View for the main activity
		setContentView(R.layout.activity_trip_sort);
		// getActionBar() returns reference to the activity's ActionBar
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trip_sorter, menu);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		switch ( item.getItemId() ) {
			case R.id.action_sort_trips:
				dialogFragment = 
						SortFragmentActivity.newInstance( "Sort Your Trips" );
				dialogFragment.show( getFragmentManager(), "Sorting Trips Dialog Fragment" );
				break;
			case R.id.action_map:
				this.finish();
				break;
		}
		return true;
	}
	
	/**
	 * 
	 */
	public void PositiveButton() {
		// do nothing, replace later with figuring out how to enable or disable
		// the button
	}
	
	/**
	 * NegativeButton is just for handling the cancel button of
	 * a dialog. No code is added here.
	 */
	public void NegativeButton() {
		// do nothing; just closes the DialogFragment
	}
	
	/**
	 * 
	 */
	public void getAllTripsFromDatabase() {
		dbAdapter.open();
		trips = dbAdapter.getAllTrips();
		dbAdapter.close();
	}
	
	public List<Route> getTrips() {
		Collections.sort( trips, Route.TRIPNAME_ORDER );
		return trips;
	}
	
	public void deliverSortOption( int option ) { 
		switch( option ) {
			case 0:
				sortData( "Trips" );
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;				
		}
	}
	
	@Override
	public void finish() {
		super.finish();
	}
}
