package com.cs390h.triptimer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RouteButtonFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_route, container, false);
        
        // Adds a new random route to the database
        rootView.findViewById( R.id.add_route_button)
        	.setOnClickListener( new View.OnClickListener() {
        		@Override
        		public void onClick( View view ) {
        			// add code to create random Route
        			Route route = RouteRandom.randomRouteFactory();
        		}
        	});
        
        return rootView;
    }	
}
