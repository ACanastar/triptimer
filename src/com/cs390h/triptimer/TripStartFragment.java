package com.cs390h.triptimer;

import com.cs390h.triptimer.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class TripStartFragment extends DialogFragment{
	private LayoutInflater inflater;
	private View view; // The fragments view
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {    
		// Use Builder to make alert dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		// Set the dialog's features
		builder.setTitle(R.string.trip_start_title);
		
		// Set dialog's view
		inflater = getActivity().getLayoutInflater();
		view = inflater.inflate(R.layout.trip_start_fragment, null);
		builder.setView(view);
			
		// Set Dialog buttons
		builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            	// get dialog's edit text
            	EditText tripText = (EditText)view.findViewById(R.id.trip_prompt);
            	EditText routeText = (EditText)view.findViewById(R.id.route_prompt);
            	// Call positveButton() in activity
            	((TripTimerActivity)getActivity()).positiveButton(tripText, routeText);
            	}
            });
		
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            	((TripTimerActivity)getActivity()).negativeButton();
            	}
            });
		// Get the AlertDialog from create()
		AlertDialog dialog = builder.create();
		
		return dialog;
	}
}
