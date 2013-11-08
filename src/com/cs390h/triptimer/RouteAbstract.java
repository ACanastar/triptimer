package com.cs390h.triptimer;

import java.util.Date;

import com.google.android.gms.maps.model.LatLng;

/**
 * Route objects are a collection of getter and setter methods
 * for variables recorded and displayed by TripTimer. Route objects
 * are populated by setter methods as the TripTimer is 
 * running or by queries to the database. Data from the Route
 * objects is to be used to populate AllTrips... and AveragesBy...
 * Fragments in the Trips activity.
 *   
 * @author andrew.canastar and john.qualls
 * @version 2013.10.31
 *
 */
public class RouteAbstract implements RouteInterface {

	protected String routeName, timeOfDay, tripName, tripDate;
	protected Long tripTime;
	protected double latitude, longitude;
	protected LatLng coord;
	//protected Date tripDate;
	
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#setTripName(java.lang.String)
	 */
	@Override
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#getTripName()
	 */
	@Override
	public String getTripName() {
		return tripName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#setRouteName(java.lang.String)
	 */
	@Override
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#getRouteName()
	 */
	@Override
	public String getRouteName() {
		return routeName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#setTripTime(long)
	 */
	@Override
	public void setTripTime(Long tripTime) {
		this.tripTime = tripTime;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#getTripTime()
	 */
	@Override
	public Long getTripTime() {
		return tripTime;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#setTripDate(long)
	 */
	@Override
	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#getTripDate()
	 */
	@Override
	public String getTripDate() {
		return tripDate.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#setTimeOfDay(java.lang.String)
	 */
	@Override
	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#getTimeOfDay()
	 */
	@Override
	public String getTimeOfDay() {
		return timeOfDay;
	}
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#getLatitude()
	 */
	@Override
	public double getLatitude() {
		return this.latitude;
	}
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#setLatitude(long)
	 */
	@Override
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#getLongitude()
	 */
	@Override
	public double getLongitude() {
		return this.longitude;
	}
	/*
	 * (non-Javadoc)
	 * @see com.cs390h.triptimer.RouteInterface#setLongitude(long)
	 */
	@Override
	public void setLongitude(double longitude) {
		this.longitude = longitude;		
	}	
}
