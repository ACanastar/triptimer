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
		super( getRandomRouteName(),
				getRandomTimeOfDay(),
				true,
				getRandomTripTime()	);
	}
	
	
	
	/**
	 * Returns a String of random English alphabet letters
	 * that is length 10.
	 * 
	 * @return String routeName
	 */
	private static String getRandomRouteName() {
		char[] temp = new char[10];
		Random random = new Random();
		for( char i : temp ) {
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
		Random random = new Random();
		Long tripTime = random.nextLong();
		return tripTime;
	}
	
	/**
	 * Returns a randomly generated time of day category.
	 * 
	 * @return String timeOfDay
	 */
	private static String getRandomTimeOfDay() {
		Random random = new Random();
		int timeNum = random.nextInt( 8 );
		return TimesOfDay.getTimeOfDay( timeNum );
	}
}
