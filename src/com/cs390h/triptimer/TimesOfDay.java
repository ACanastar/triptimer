package com.cs390h.triptimer;

public class TimesOfDay {
	
	private static final String[] timesOfDay = {
		"EARLY_MORNING",
		"MORNING_RUSH",
		"LATE_MORNING",
		"LUNCH",
		"LATE_AFTERNOON",
		"EVENING_RUSH",
		"LATE_EVENING",
		"NIGHT"		
	};


	/**
	 * 
	 * @param dayNum
	 * @return
	 */
	public static String getTimeOfDay( int timeNum ) {
		return TimesOfDay.timesOfDay[timeNum];
	}
	
	/**
	 * Will be used in the final implementation to convert
	 * a Time variable to a specific time of day description.
	 */
	public static void TimeOfDaySwitch( ) {
		
	}
}
