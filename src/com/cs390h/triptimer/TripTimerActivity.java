package com.cs390h.triptimer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import android.location.Location;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.IntentSender;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.cs390h.triptimer.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationListener;

public class TripTimerActivity extends Activity implements 
LocationListener,
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener {
	
	// Stores the current instantiation of the location client in this object
    private LocationClient mLocationClient;
    
    // Global variable to hold the current device location
    private Location mCurrentLocation;
    
    // Used for current location update interval and accuracy
    private LocationRequest mLocationRequest;
    
    // Used to start and stop time
    private boolean timeStart;
    
    // Map objects
    private GoogleMap map;
    private MapFragment mapFrag;
    private Marker currPosition;
    private Marker destination;
 
    // Fragment objects
    // private FragmentManager manager;
    // private FragmentTransaction transaction;
    
    //TEMPORARY Route objects, needs to be removed when database is used
    private Route route;
    private List<Route> trips;
    
    // Time variables
    private long startTime;
    private long endTime;
    private long elapsedTime;
    
    // The variable for interfacing with the database adapter class
    private TripTimerDbAdapter dbAdapter;
    
 
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		dbAdapter = new TripTimerDbAdapter( this );
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_timer);
		
		// Initialize application
		init();
	}
	
	/**
	 * Sets up the application.
	 */
	private void init() {
		// Get the google map object from fragment
		mapFrag = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
		map = mapFrag.getMap();
		
		/*
         * Create a new location client, using the enclosing class to
         * handle callbacks.
         */
        mLocationClient = new LocationClient(this, this, this);
        
		/*
		 *  Set up the location update interval and accuracy
		 */
        mLocationRequest = LocationRequest.create();
        // Use high accuracy
        mLocationRequest.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(5000);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(1000);
		
		/*
		 * create listeners
		 */
		listeners();
	}

	/**
	 * Contains all the anonymous inner class listeners for the app
	 */
	private void listeners() {
		
		/*
		 * Map click listener
		 */
		map.setOnMapClickListener (new GoogleMap.OnMapClickListener(){
			public void onMapClick(LatLng point) {
				/*
				 * Create route and add a target marker
				 */
				route = new Route();
				route.setLatitude(point.latitude); // set route latitude coordinates
				route.setLongitude(point.longitude); // set route longitude
				
				// Reset location updates
				locationUpdates(false);
				
				// Open up trip start fragment
				TripStartFragment fragment = new TripStartFragment();
						fragment.show(getFragmentManager(), "trip_start");
						
				addMarker(point, "Target", false);	
			}
		});
		
	}
	
	/*
     * Called when the Activity becomes visible.
     */
    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        mLocationClient.connect();
    }

	/*
     * Called when the Activity is no longer visible.
     */
    @Override
    protected void onStop() {
    	
        super.onStop();
    }
    
    @Override
	protected void onDestroy() {
    	// Stop location updates if enabled
        if(destination != null) {
        	locationUpdates(false);
        }
        // Disconnecting the client invalidates it.
        mLocationClient.disconnect();
		super.onDestroy();
	}

	/**
     * Turns location updates on and off.
     * @param enable Either enable or disable location updates.
     */
	private void locationUpdates(boolean enable) {
		// Stop location updates if enabled
        if(enable) {
        	mLocationClient.requestLocationUpdates(mLocationRequest,
					(LocationListener)this);
        }
        else
        	mLocationClient.removeLocationUpdates(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trip_timer, menu);
		return true;
	}
	
	/*
	 * Listener for all the action bar items
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_open:
	        	// Open OpenFragment
	        	OpenFragment open = new OpenFragment();
	    		open.show(getFragmentManager(), "open");
	            return true;
	        case R.id.action_start_trips:
	        	// Start the trips data analysis activity
	        	Intent intent = new Intent( this, TripSortActivity.class );
	        	startActivity( intent );
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		/*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {

                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST);

                /*
                * Thrown if Google Play services canceled the original
                * PendingIntent
                */

            } catch (IntentSender.SendIntentException e) {

                // Log the error
                e.printStackTrace();
            }
        } else {

            // If no resolution is available, display a dialog to the user with the error.
            showErrorDialog(connectionResult.getErrorCode());
        }
		
	}
	
	/**
     * Show a dialog returned by Google Play services for the
     * connection error code
     *
     * @param errorCode An error code returned from onConnectionFailed
     */
    private void showErrorDialog(int errorCode) {

        // Get the error dialog from Google Play services
        Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
            errorCode,
            this,
            LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST);

        // If Google Play services can provide an error dialog
        if (errorDialog != null) {

            // Create a new DialogFragment in which to show the error dialog
            ErrorDialogFragment errorFragment = new ErrorDialogFragment();

            // Set the dialog in the DialogFragment
            errorFragment.setDialog(errorDialog);

            // Show the error dialog in the DialogFragment
            errorFragment.show(getFragmentManager(), LocationUtils.APPTAG);
        }
    }
    
    @Override
	public void onLocationChanged(Location location) {
    	// Get the start time
    	if(timeStart){
    		startTime = location.getTime();
    		Toast.makeText(this, "Trip Started", Toast.LENGTH_SHORT).show();
    		timeStart = false;
    	}
    	
		// Update the device's current location marker
    	LatLng coord = new LatLng(location.getLatitude(),
    							location.getLongitude());
		addMarker(coord, "Your Location", false);	
		
		/*
		 * Check if user has reached destination
		 */
		LatLng coord2 = destination.getPosition();
		// first position of array contains distance in meters
		// between target to destination.
		float[] results = new float[3];
		Location.distanceBetween(coord.latitude, 
								coord.longitude, coord2.latitude, 
								coord2.longitude, results);
		// distance in meters
		float result = results[0];
		
		/*
		 *  Save trip if destination is reached
		 */
		if(result <= 20) {
			timeStart = true; // reset time start for next trip
			
			// Calculate length of trip in milliseconds
			endTime= location.getTime();
			elapsedTime = (endTime - startTime);
			Toast.makeText(this, "Trip Time: " + elapsedTime + " Minutes", 
					Toast.LENGTH_SHORT).show();
			
			// Remove destination and set it to null
			destination.remove();
			destination = null;
			locationUpdates(false); // turn off location updates
			
			// Set the rest of the Trip's fields
			setTrip();
		}
	}
    
    /*
     * Sets the trip's fields. This is where the trip should 
     * stored in the database.
     */
    @SuppressLint("SimpleDateFormat")
	private void setTrip() {	
    	// Get current time/date
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	String str = dateFormat.format(date);
    	StringTokenizer tkn = new StringTokenizer(str, " ");
    	// Set day and time of trip
    	route.setTripDate(tkn.nextToken()); // first token contains the day
    	route.setTimeOfDay(tkn.nextToken()); // second token contains the time of day
		
    	// Set duration of trip
    	long tripTime = elapsedTime; // In milliseconds
    	route.setTripTime( tripTime );    	
    	
    	// Send route to the database
    	this.newRouteToDatabase( route );
	}

	/**
     * Adds a marker to the map.
     * @param location The location of the marker.
     * @param title Title of the marker.
     * @param moveCamera Whether or not to move camera to marker.
     */
	private void addMarker(LatLng coord, String title, boolean moveCamera) {
		// Make marker at the location
		if(title.equals("Your Location")) {
			if(currPosition != null)
				currPosition.remove();
				currPosition = map.addMarker(new MarkerOptions()
				    		.position(coord)
				    		.title(title));	
			}
		else if(title.equals("Target")){// must be destination marker
			if(destination != null) 
				destination.remove();
					
    		// add the destination marker on the map
			destination = map.addMarker(new MarkerOptions()
			    			.position(coord)
			    			.title(title)
			    			.icon(BitmapDescriptorFactory.fromAsset("target_icon.png")));
			}
		
		if(moveCamera) {
			// Move camera to marker
			map.moveCamera(CameraUpdateFactory.zoomTo(15f));
			map.moveCamera(CameraUpdateFactory.newLatLng(coord));
		}
	}

	@Override
	public void onConnected(Bundle arg0) {
		// Start location updates if needed
	        if(destination != null)
	        	locationUpdates(true);
	        
		// Get the current location
		mCurrentLocation = mLocationClient.getLastLocation();
		
		// Add current location to map
		LatLng coord = new LatLng(mCurrentLocation.getLatitude(),
								mCurrentLocation.getLongitude());
		addMarker(coord, "Your Location", true);
	}
	
	@Override
	public void onDisconnected() {
		Toast.makeText(this, "Current Location Disconnected", Toast.LENGTH_SHORT).show();
		
	}
	
	/*
     * Handle results returned to this Activity by other Activities started with
     * startActivityForResult(). In particular, the method onConnectionFailed() in
     * LocationUpdateRemover and LocationUpdateRequester may call startResolutionForResult() to
     * start an Activity that handles Google Play services problems. The result of this
     * call returns here, to onActivityResult.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        // Choose what to do based on the request code
        switch (requestCode) {

            // If the request code matches the code sent in onConnectionFailed
            case LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST :

                switch (resultCode) {
                    // If Google Play services resolved the problem
                    case Activity.RESULT_OK:

                        // Log the result
                        Log.d(LocationUtils.APPTAG, getString(R.string.resolved));
                    break;

                    // If any other result was returned by Google Play services
                    default:
                        // Log the result
                        Log.d(LocationUtils.APPTAG, getString(R.string.no_resolution));
                    break;
                }

            // If any other request code was received
            default:
               // Report that this Activity received an unknown requestCode
               Log.d(LocationUtils.APPTAG,
                       getString(R.string.unknown_activity_request_code, requestCode));

               break;
        }
    }	
    
    /**
     * Define a DialogFragment to display the error dialog generated in
     * showErrorDialog.
     */
    public static class ErrorDialogFragment extends DialogFragment {

        // Global field to contain the error dialog
        private Dialog mDialog;

        /**
         * Default constructor. Sets the dialog field to null
         */
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }

        /**
         * Set the dialog to display
         *
         * @param dialog An error dialog
         */
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }

        /*
         * This method must return a Dialog to the DialogFragment.
         */
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }

    /*
     * This is called from the start trip dialog
     */
	public void positiveButton(EditText tripText, EditText routeText) {
		/*
		 * Set route's name from dialog fragment
		 */
		String tripName = "";
		String routeName = "";
		tripName = tripText.getText().toString();
		routeName = routeText.getText().toString();
	    // Check for blank filename
	    if(tripName.contentEquals(""))
	    	tripName = "UNTITLED";
	    if(routeName.contentEquals(""))
	    	routeName = "UNTITLED";
		route.setTripName(tripName);
		route.setRouteName( routeName );
		
		beginTrip();
	}

	/*
	 * Starts the trip process
	 */
	private void beginTrip() {
		locationUpdates(true);
		// Start time
		timeStart = true;	
	}

	/*
	 * Cancel the trip by removing marker and stop requests
	 */
	public void negativeButton() {
		destination.remove();
		destination = null;
		locationUpdates(false);
	}
	
	/*
	 * Gets the Trip names for the open fragment dialog
	 */
	public String[] getTripNames() {
		// Initialize routes variable with all Routes from databse
		this.getAllTripsFromDatabase();
		trips = getTrips();
		String[] array;
		// Return empty String array if there are no trips
		if(trips.size() == 0) {
			array = new String[1];
			array[0] = "NO TRIPS CREATED";
			return array;
		}
		else {
			array = new String[trips.size()];
			for(int i = 0; i < trips.size(); i++)
				array[i] = trips.get(i).tripName;
			return array;
		}
	}

    /*
	 * Opens up a previously saved trip
	 */
	public void openFile(int trip) {
		
		// Do nothing if there are no trips
	
		if(trips.size() == 0)
			return;
		else {
			// get trip
			route = trips.get(trip);
			LatLng coord = new LatLng( route.getLatitude(),
										route.getLongitude() );
			
			/*
			 * open trip
			 */
			// remove previous marker, if there is one
			if(destination != null) {
				destination.remove();
				destination = null;
			}	
			// add marker
			addMarker(coord, "Target", true);
			beginTrip();
		}
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
	
	/**
	 * Just for canceling.
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
}
