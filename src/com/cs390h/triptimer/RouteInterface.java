package com.cs390h.triptimer;

import java.util.Date;
import java.util.Comparator;

import com.google.android.gms.maps.model.LatLng;

/**
 * RouteInterface defines the getter and setter methods for a 
 * Route object. Implementations of this interface should have
 * an instance variable for each getter/setter pair. Route objects
 * will be populated by setter methods as the TripTimer is 
 * running or by queries to the database. Data from the Route
 * objects is to be used to populate AllTrips... and AveragesBy...
 * Fragments in the Trips activity.
 *   
 * @author andrew.canastar and john.qualls
 * @version 2013.10.31
 *
 */

public interface RouteInterface {
	
	/**
	 * Comparator for ordering objects that implement the RouteInterface
	 * by the date of the trip.
	 */
	static final Comparator<RouteInterface> TRIPDATE_ORDER = 
			new Comparator<RouteInterface>() {
		@Override
		public int compare(RouteInterface r1, RouteInterface r2) {
			return r1.getTripDate().compareTo( r2.getTripDate() );
		}
	};
	
	/**
	 * Comparator for ordering objects that implement the RouteInterface
	 * by the duration of the trip.
	 */
	static final Comparator<RouteInterface> TRIPTIME_ORDER = 
			new Comparator<RouteInterface>() {
		@Override
		public int compare(RouteInterface r1, RouteInterface r2) {
			return r1.getTripTime().compareTo( r2.getTripTime() );
		}
	};
	
	/**
	 * Comparator for ordering objects that implement the RouteInterface
	 * by their route name.
	 */
	static final Comparator<RouteInterface> ROUTENAME_ORDER = 
			new Comparator<RouteInterface>() {
		@Override
		public int compare(RouteInterface r1, RouteInterface r2) {
			return r1.getRouteName().compareTo( r2.getRouteName() );
		}
	};
	
	/**
	 * Comparator for ordering objects that implement the RouteInterface
	 * by their time of day.
	 */
	static final Comparator<RouteInterface> TIMEOFDAY_ORDER = 
			new Comparator<RouteInterface>() {
		@Override
		public int compare(RouteInterface r1, RouteInterface r2) {
			return r1.getTimeOfDay().compareTo( r2.getTimeOfDay() );
		}
	};
	
	/**
	 * Comparator for ordering objects that implement the RouteInterface
	 * by their trip name.
	 */
	static final Comparator<RouteInterface> TRIPNAME_ORDER = 
			new Comparator<RouteInterface>() {
		@Override
		public int compare(RouteInterface r1, RouteInterface r2) {
			return r1.getTripName().compareTo( r2.getTripName() );
		}
	};
	
	
	/**
	 * Setter method for tripName.
	 * @param tripName
	 */
	public void setTripName( String tripName );
	
	/**
	 * Getter method for tripName.
	 * @return String tripName
	 */
	public String getTripName();
	
	/**
	 * Setter method for routeName.
	 * @param routeName
	 */
	public void setRouteName( String routeName );
	
	/**
	 * Getter method for routeName.
	 * @return String routeName
	 */
	public String getRouteName();
	
	/**
	 * Setter method for tripTime.
	 * @param tripTime
	 */
	public void setTripTime( Long tripTime );
	
	/**
	 * Getter method for tripTime.
	 * @return Long tripTime
	 */
	public Long getTripTime();
	
	/**
	 * Setter method for tripDate.
	 * @param tripDate
	 */
	public void setTripDate( String tripDate );
	
	/**
	 * Getter method for tripDate.
	 * @return Date tripDate
	 */
	public String getTripDate();
	
	/**
	 * Setter method for timeOfDay.
	 * @param timeOfDay
	 */
	public void setTimeOfDay( String timeOfDay );
	
	/**
	 * Getter method for timeOfDay.
	 * @return String timeOfDay
	 */
	public String getTimeOfDay();
	
	/**
	 * Getter method for latitude.
	 * @return double latitude in degrees.
	 */
	public double getLatitude();
	
	/**
	 * Setter method for latitude.
	 * @param latitude in degrees
	 */
	public void setLatitude( double latitude);
	
	/**
	 * Getter method for longitude.
	 * @return double longitude in degrees.
	 */
	public double getLongitude();
	
	/**
	 * Setter method for latitude.
	 * @param longitude in degrees
	 */
	public void setLongitude( double longitude);
}
