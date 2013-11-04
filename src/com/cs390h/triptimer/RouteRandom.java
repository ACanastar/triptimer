package com.cs390h.triptimer;

import java.util.Random;

/**
 * Makes Random Routes for testing.
 * 
 * @author andrew.canastar
 * @version 2013.11.01
 *
 */
public class RouteRandom extends Route {	
	
	private static final String[] routeNames = { "RouteA", "RouteB", "RouteC", "RouteD" };
	private static final int NUM_ROUTES = 4;
	private static final String[] tripNames = { "TripA", "TripB", "TripC", "TripD" };
	private static final int NUM_TRIPS = 4;
	private static Random random = new Random();
	
	/**
	 * For testing. A factory that creates random routes
	 * @return Route object
	 */
	public static Route randomRouteFactory() {
		return new RouteRandom();
	}
	
	/**
	 * Default constructor for making a random route.
	 */
	public RouteRandom() {
		super( 	getRandomTripName(),
				getRandomRouteName(),
				getRandomTimeOfDay(),
				true,
				getRandomTripTime()	);
	}
	
	/**
	 * Returns a String tripName from a defined set of names.
	 * 
	 * @return String trpName
	 */
	private static String getRandomTripName() {
		//Random random = new Random();
		int nextInt = random.nextInt( NUM_TRIPS );
		return tripNames[nextInt];
	}
	
	/**
	 * Returns a String routeName from a defined set of names.
	 * 
	 * @return String routeName
	 */
	private static String getRandomRouteName() {
		//Random random = new Random();
		int nextInt = random.nextInt( NUM_ROUTES );
		return routeNames[nextInt];
	}
	
	/**
	 * Returns a String of random English alphabet letters
	 * that is length 10.
	 * 
	 * @return String routeName
	 */
	private static String getRandomName() {
		char[] temp = new char[10];
		//Random random = new Random();
		for( int i = 0; i < 10; i++ ) {
			temp[i] = (char)( random.nextInt( 26 ) + 65 );
		}
		return new String( temp );
	}
	
	/**
	 * Return a randomly generated Long trip time that simulates 
	 * a calculated trip time in milliseconds.
	 * 
	 * @return Long tripTime
	 */
	private static Long getRandomTripTime() {
		
		Long tripTime = random.nextLong();
		return tripTime;
	}
	
	/**
	 * Returns a randomly generated time of day category.
	 * 
	 * @return String timeOfDay
	 */
	private static String getRandomTimeOfDay() {
		//Random random = new Random();
		int timeNum = random.nextInt( 8 );
		return TimesOfDay.getTimeOfDay( timeNum );
	}
}
