package com.cs390h.triptimer;

import com.cs390h.triptimer.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class OpenFragment extends DialogFragment{	
	private String[] tripNames;
	private TripTimerActivity activity;
	
 	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
 		// Get activity reference
 		activity = (TripTimerActivity)getActivity();
 		
 		// Get the filenames
 		getTripNames();
		// Use Builder to make alert dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		// Set the dialog's features
		builder.setTitle(R.string.open_frag_title);
		
		builder.setItems(tripNames, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	activity.openFile(which);
        }
		});
		// Get the AlertDialog from create()
		AlertDialog dialog = builder.create();
		
		return dialog;
	}
 	
 	/*
 	 * Gets the names of the Trips
 	 */
	private void getTripNames() {
		tripNames = activity.getTripNames();
	}
}
