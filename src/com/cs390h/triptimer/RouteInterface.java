package com.cs390h.triptimer;

import java.util.Comparator;

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
