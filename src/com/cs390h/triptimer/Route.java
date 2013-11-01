package com.cs390h.triptimer;

import java.util.Date;

public class Route extends AbstractRoute {
	
	/**
	 * Default constructor; possibly better for it to automatically
	 * update timeOfDay and tripTime
	 */
	public Route() {
		// provided by the user
		this.routeName = null;
		// could be automatically set by the constructor?		
		this.timeOfDay = null;
		// could be automatically set by the constructor?
		this.tripDate = null;
		// calculated and set
		this.tripTime = null;
	}	
	
	/**
	 * 
	 * @param routeName
	 */
	public Route( String routeName ) {
		// provided by the user
		this.routeName = routeName;
		// could be automatically set by the constructor?		
		this.timeOfDay = null;
		// could be automatically set by the constructor?
		this.tripDate = null;
		// calculated and set
		this.tripTime = null;
	}	
	
	/**
	 * 
	 * @param routeName
	 * @param timeOfDay
	 */
	public Route( String routeName, String timeOfDay ) {
		// provided by the user
		this.routeName = routeName;
		// could be automatically set by the constructor?		
		this.timeOfDay = timeOfDay;
		// could be automatically set by the constructor?
		this.tripDate = null;
		// calculated and set
		this.tripTime = null;
	}	
	
	/**
	 * 
	 * @param routeName
	 * @param timeOfDay
	 * @param tripTime
	 */
	public Route( String routeName, String timeOfDay, Long tripTime ) {
		// provided by the user
		this.routeName = routeName;
		// could be automatically set by the constructor?		
		this.timeOfDay = timeOfDay;
		// could be automatically set by the constructor?
		this.tripDate = null;
		// calculated and set
		this.tripTime = tripTime;
	}
	
	/**
	 * 
	 * @param routeName
	 * @param timeOfDay
	 * @param setDate
	 * @param tripTime
	 */
	public Route( String routeName, String timeOfDay, boolean setDate, Long tripTime ) {
		// provided by the user
		this.routeName = routeName;
		if( setDate ) {
			this.tripDate = new Date();
		}
		this.timeOfDay = timeOfDay;		
		// calculated and set
		this.tripTime = tripTime;
	}
}
