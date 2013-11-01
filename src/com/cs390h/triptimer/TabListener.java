package com.cs390h.triptimer;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.util.Log;
/**
 * Following guidelines provided by:
 * http://www.androidbegin.com/tutorial/implementing-fragment-tabs-in-android/
 * The tutorial gives the skeleton of a very clean implementation of
 * fragments in tabs.
 * 
 * @author andrew.canastar
 * @version 2013.10.31
 *
 */

public class TabListener implements ActionBar.TabListener {
	
	private Fragment fragment;
	
	public TabListener( Fragment fragment ) {
		this.fragment = fragment;
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.replace(R.id.fragment_container, fragment);		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.remove( fragment );	
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		Log.d("Tab", String.valueOf(tab.getPosition()) + " unselected");	
	}
}
