package com.cs390h.triptimer;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;


public class SortFragmentActivity extends DialogFragment {

	AlertDialog diag;
	final String[] fromColumns = { "Trips", "Routes", "Trip Time", "Time of Day", "Date" };
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	static SortFragmentActivity newInstance( String title ) {
		SortFragmentActivity fragment = new SortFragmentActivity();
		Bundle args = new Bundle();
		args.putString( "title", title);
		fragment.setArguments( args );
		return fragment;
	}
	
	/**
	 * 
	 * @param savedInstanceState
	 * @return 
	 */
	@Override
	public Dialog onCreateDialog( Bundle savedInstanceState ) {
		
		//int[] toViews = { R.id.sort_container };		
		
		
		String title = getArguments().getString( "title" );
		ArrayAdapter<String> adapter = new ArrayAdapter<String>( getActivity(),
														R.layout.sort_options_textview,
														fromColumns);
		//LayoutInflater inflater = getActivity().getLayoutInflater();
		diag = new AlertDialog.Builder( getActivity() )
			// Inflate and set the layout for the dialog
		    // Pass null as the parent view because its going in the dialog layout
			//.setView( inflater.inflate( R.layout.dialog_sortroute, null ) )
			.setIcon( R.drawable.ic_launcher )
			.setTitle( title )
			.setAdapter(adapter, new DialogInterface.OnClickListener()  {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//((TripTimerActivity)getActivity() ).deliverSortOption(which);					
				}
			})
			.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
				public void onClick( DialogInterface dialog, int whichButton ) {
					((TripTimerActivity) getActivity() ).NegativeButton();
				}
			}).create();
		return diag;
	}
}
