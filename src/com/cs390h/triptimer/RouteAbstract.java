package com.cs390h.triptimer;

import java.util.Comparator;

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
	protected static final int NUM_VAR = 5;
	
	/**
	 * Comparator for ordering objects that implement the RouteInterface
	 * by the date of the trip.
	 */
	protected static final Comparator<RouteInterface> TRIPDATE_ORDER = 
			new Comparator<RouteInterface>() {
		@Override
		public int compare(RouteInterface r1, RouteInterface r2) {
			int result = r1.getTripDate().compareTo( r2.getTripDate() );
			if( result != 0 ) {
				return result;
			} else {
				int i = 0;
				int l = NUM_VAR;
				while( result == 0 && i < l ) {
					result = compareOnNextRouteVariable( i, r1, r2 );
					i++;
				}
			}
			if( result == 0 ) {
				return -1;
			} else {
				return result;
			}						
		}
	};
	
	/**
	 * Comparator for ordering objects that implement the RouteInterface
	 * by the duration of the trip.
	 */
	protected static final Comparator<RouteInterface> TRIPTIME_ORDER = 
			new Comparator<RouteInterface>() {
		@Override
		public int compare(RouteInterface r1, RouteInterface r2) {
			int result = r1.getTripTime().compareTo( r2.getTripTime() );
			if( result != 0 ) {
				return result;
			} else {
				int i = 0;
				int l = NUM_VAR;
				while( result == 0 && i < l ) {
					result = compareOnNextRouteVariable( i, r1, r2 );
					i++;
				}
			}
			if( result == 0 ) {
				return -1;
			} else {
				return result;
			}
		}
	};
	
	/**
	 * Comparator for ordering objects that implement the RouteInterface
	 * by their route name.
	 */
	protected static final Comparator<RouteInterface> ROUTENAME_ORDER = 
			new Comparator<RouteInterface>() {
		@Override
		public int compare(RouteInterface r1, RouteInterface r2) {
			int result = r1.getRouteName().compareTo( r2.getRouteName() );
			if( result != 0 ) {
				return result;
			} else {
				int i = 0;
				int l = NUM_VAR;
				while( result == 0 && i < l ) {
					result = compareOnNextRouteVariable( i, r1, r2 );
					i++;
				}
			}
			if( result == 0 ) {
				return -1;
			} else {
				return result;
			}
		}
	};
	
	/**
	 * Comparator for ordering objects that implement the RouteInterface
	 * by their time of day.
	 */
	protected static final Comparator<RouteInterface> TIMEOFDAY_ORDER = 
			new Comparator<RouteInterface>() {
		@Override
		public int compare(RouteInterface r1, RouteInterface r2) {
			int result = r1.getTimeOfDay().compareTo( r2.getTimeOfDay() );
			if( result != 0 ) {
				return result;
			} else {
				int i = 0;
				int l = NUM_VAR;
				while( result == 0 && i < l ) {
					result = compareOnNextRouteVariable( i, r1, r2 );
					i++;
				}
			}
			if( result == 0 ) {
				return -1;
			} else {
				return result;
			}
		}
	};
	
	/**
	 * Comparator for ordering objects that implement the RouteInterface
	 * by their trip name.
	 */
	protected static final Comparator<RouteInterface> TRIPNAME_ORDER = 
			new Comparator<RouteInterface>() {
		@Override
		public int compare(RouteInterface r1, RouteInterface r2) {
			int result = r1.getTripName().compareTo( r2.getTripName() );
			if( result != 0 ) {
				return result;
			} else {
				int i = 0;
				int l = NUM_VAR;
				while( result == 0 && i < l ) {
					result = compareOnNextRouteVariable(i, r1, r2 );
					i++;
				}
			}
			if( result == 0 ) {
				return -1;
			} else {
				return result;
			}
		}
	};
	
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

	/**
	 * Contains a switch statement that runs through all
	 * compareTo's on each Route variable.
	 * @param String name of Route variable.
	 * @return comparison result: -1, 0, or +1
	 */
	private static int compareOnNextRouteVariable( int v, 
													RouteInterface r1, 
													RouteInterface r2 ) {
		int result = 0;
		switch( v ) {
			case 0:
				result = r1.getTripName().compareTo( r2.getTripName() );
			case 1:
				result = r1.getRouteName().compareTo( r2.getRouteName() );
			case 2:
				result = r1.getTripDate().compareTo( r2.getTripDate() );
			case 3:
				result = r1.getTimeOfDay().compareTo( r2.getTimeOfDay() );
			case 4:
				result = r1.getTripTime().compareTo( r2.getTripTime() );
		}
		return result;
	}	
}
