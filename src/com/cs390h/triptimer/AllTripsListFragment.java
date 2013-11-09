package com.cs390h.triptimer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class AllTripsListFragment extends ListFragment {
	
	TripSortActivity activity;
	List<Route> routes;
	
	/**
	 * Custom ArrayAdapter for putting Trip data into the row layout
	 * of the listview.
	 * 
	 * @author andrew.canastar
	 * @version 2013.11.03
	 *
	 */
	private class TripArrayAdapter extends ArrayAdapter<Route> {

		private List<Route> routes;
		private Context context;
		
		/**
		 * 
		 * @param context
		 * @param resource
		 * @param routes
		 */
		public TripArrayAdapter(Context context, int resource, List<Route> routes) {
			super(context, resource, routes);
			this.context = context;
			this.routes = new ArrayList<Route>();
			this.routes.addAll( routes );
		}
		
		/*
		 * (non-Javadoc)
		 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView( int position, View convertView, ViewGroup parent ) {			
			
			//creating LayoutInflator for inflating the row layout.
	        LayoutInflater inflator = (LayoutInflater)context.getSystemService
	        		(Context.LAYOUT_INFLATER_SERVICE);
	        
	        //inflating the row layout we defined earlier.
	        convertView = inflator.inflate(R.layout.trip_alldata_row, null);
	        
	        // set the trip_name TextView in the row layout
	        TextView trip = (TextView)convertView.findViewById(R.id.trip_name);
	        trip.setText( routes.get(position).getTripName() );
	        // set the route_name TextView in the row layout
	        TextView route = (TextView)convertView.findViewById(R.id.route_name);
	        route.setText( routes.get(position).getRouteName() );
	        // set the trip time TextView in the row layout
	        TextView triptime = (TextView)convertView.findViewById(R.id.triptime);
	        triptime.setText( getDurationBreakdown( routes.get(position).getTripTime()) );
	        // set the time of day TextView in the row layout
	        TextView timeofday = (TextView)convertView.findViewById(R.id.timeofday);
	        timeofday.setText( routes.get(position).getTimeOfDay() );
	        // set the date TextView in the row layout
	        TextView date = (TextView)convertView.findViewById(R.id.date);
	        date.setText( routes.get(position).getTripDate() );
	        
			return convertView;
		}
		
		/**
		 * 
		 * @param sortingOn
		 */
		/*public void sortData( String sortingOn ) {
		     
		}*/
		
		/**
		 * Refreshes the arrayadapter and updates the listview.
		 * 
		 * see: http://vikinghammer.com/
		 * 2011/06/17/
		 * android-listview-maintain-your-
		 * scroll-position-when-you-refresh/
		 * 
		 * @param sortedRoutes, a refreshed list of routes, sorted on some variable
		 */
		/*public void refill( List<Route> sortedRoutes ) {
		    routes.clear();
		    routes.addAll( sortedRoutes );
		    notifyDataSetChanged();
		}*/
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach( Activity activity ) {
		this.activity = (TripSortActivity) activity;
		super.onAttach( this.activity );
		this.activity.getAllTripsFromDatabase();
		this.routes = this.activity.getTrips();
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		TripArrayAdapter tripAdapter = new TripArrayAdapter( getActivity(), 
				R.layout.trip_alldata_row,
				this.routes );
		setListAdapter( tripAdapter );
       // View rootView = inflater.inflate(R.layout.trips_list, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
	
	/**
     * Convert a millisecond duration to a string format
     * This is blatantly ripped off from:
     * http://stackoverflow.com/questions/625433/
     * how-to-convert-milliseconds-to-x-mins-x-seconds-
     * in-java/625624#625624
     * 
     * @param millis A duration to convert to a string form
     * @return A string of the form "X Days Y Hours Z Minutes A Seconds".
     */
    public static String getDurationBreakdown(Long millis)
    {
        if(millis < 0)
        {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        sb.append(days);
        sb.append(" Days ");
        sb.append(hours);
        sb.append(" Hours ");
        sb.append(minutes);
        sb.append(" Minutes ");
        sb.append(seconds);
        sb.append(" Seconds");

        return(sb.toString());
    }
}
