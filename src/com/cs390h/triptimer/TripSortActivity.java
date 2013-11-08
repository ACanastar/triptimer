package com.cs390h.triptimer;

import java.util.Collections;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.Menu;

public class TripSortActivity extends Activity {
	private DialogFragment dialogFragment;
	
	
	// Just using all trips fragment for now
	Fragment allTripsFragment = new AllTripsListFragment();
	TripTimerDbAdapter dbAdapter = new TripTimerDbAdapter( this );
	List<Route> trips;
	AllTripsListFragment tripsList;
	FragmentTransaction ft = getFragmentManager().beginTransaction();
	
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
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		switch ( item.getItemId() ) {
			case R.id.sort_trips:
				dialogFragment = 
						SortFragmentActivity.newInstance( "Sort Your Trips" );
				dialogFragment.show( getFragmentManager(), "Sorting Trips Dialog Fragment" );
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
	 * 
	 * @param color
	 */
	public void PositiveButton( String color ) {
		
	}
	
	/**
	 * 
	 */
	public void NegativeButton() {
		// do nothing; just closes the DialogFragment
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
	
	/**
	 * 
	 */
	public void getAllTripsFromDatabase() {
		dbAdapter.open();
		trips = dbAdapter.getAllTrips();
		dbAdapter.close();
	}
	
	/**
	 * Swap code from http://developer.android.com/guide/components/fragments.html
	 */
	public void swapToAllTrips() {
		// Create new fragment and transaction
		ListFragment allTripsListFragment = new AllTripsListFragment();		
		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack
		ft.replace(R.id.fragment_container, allTripsListFragment);
		ft.addToBackStack(null);
		
		// Commit the transaction
		ft.commit();
	}
	
	public List<Route> getTrips() {
		Collections.sort( trips, Route.TRIPNAME_ORDER );
		return trips;
	}
	
	/*public void deliverSortOption( int option ) { 
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
	}*/
}
