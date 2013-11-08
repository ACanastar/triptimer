package com.cs390h.triptimer;

import java.util.Date;

/**
 * Has three constructors for a Route: 
 * 	1) default, 
 * 	2) a 2-arg constrcutor that assigns the trip- 
 * 		and routeNames and automatically instaniates
 * 		the TimeOfDay and TripDate variables 
 * 	3) a 5-arg constructor that instantiates all trip
 * 		variables in the constructor. Used in testing.
 * 
 * @author andrew.canastar
 * @version 2013.10.31
 *
 */
public class Route extends RouteAbstract {
	
	/**
	 * Default constructor; possibly better for it to automatically
	 * update timeOfDay and tripTime
	 */
	public Route() {
		this.routeName = null;
		this.tripName = null;
		// could be automatically set by the constructor?		
		this.timeOfDay = null;
		// could be automatically set by the constructor?
		this.tripDate = null;
		// calculated and set later
		this.tripTime = null;
		this.latitude = 0;
		this.longitude = 0;
	}
	
	/**
	 * 2-arg Constructor, mostly likely to be used
	 * in the program because it will set timeOfDay and tripDate while
	 * the user provides the trip- and routeNames.
	 * 
	 * @param tripName
	 * @param routeName
	 */
	public Route( String tripName, String routeName ) {
		this.routeName = routeName;
		this.tripName = tripName;
		// need method to automatically set this from the TimesOfDay.java
		this.timeOfDay = null;
		this.tripDate = new Date().toString();
		// calculated and set later
		this.tripTime = null;		
	}
	
	/**
	 * 5-arg Constructor includes the option to automatically set
	 * the date. Mostly for testing purposes.
	 * 
	 * @param tripName
	 * @param routeName
	 * @param timeOfDay
	 * @param setDate
	 * @param tripTime
	 */
	public Route( String tripName, String routeName, String timeOfDay, 
													boolean setDate, 
													Long tripTime ) {
		this.tripName = tripName;
		this.routeName = routeName;
		if( setDate ) {
			this.tripDate = new Date().toString();
		}
		this.timeOfDay = timeOfDay;		
		// calculated and set
		this.tripTime = tripTime;
	}
	
	/**
	 * 5-arg Constructor instantiates the tripDate from the arguments.
	 * Used in creating a Route object from a database query.
	 * 
	 * @param tripName
	 * @param routeName
	 * @param timeOfDay
	 * @param setDate
	 * @param tripTime
	 */
	public Route( String tripName, String routeName, String timeOfDay, 
													String tripDate, 
													Long tripTime,
													double latitude,
													double longtiude) {
		this.tripName = tripName;
		this.routeName = routeName;
		this.tripDate = tripDate;		
		this.timeOfDay = timeOfDay;		
		// calculated and set
		this.tripTime = tripTime;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
