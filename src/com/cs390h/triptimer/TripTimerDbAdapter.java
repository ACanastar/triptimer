package com.cs390h.triptimer;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TripTimerDbAdapter {
	
    // the database primary key
	public static final String KEY_ROWID = "_id";
    public static final String KEY_RNAME = "routename";
    public static final String TIME_NAME = "timeofday";
    public static final String TRIP_TIME = "triptime";
    public static final String TRIP_DATE = "tripdate";
    
    private DBHelper DBHelper;
    private SQLiteDatabase db;
    
    private static final String DATABASE_NAME = "trips";
	private static final String DATABASE_TABLE = "triphistory";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table triphistory"
			+ " (_id integer primary key," +
			" routename text not null," +
	        " timeofday text not null," +
	        " triptime integer not null," +
	        " tripdate text not null);";
	private static final String TAG = "TripTimerDbAdapter";
	
	private final Context context;
	
	/**
	 * 
	 * @author andrew.canastar
	 *
	 */
	private static class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);			
		}		
	}
	
	/**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context (activity) within which to work
     */
    public TripTimerDbAdapter( Context context ) {
        this.context = context;
    }
    
    /**
     * Opens the database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public TripTimerDbAdapter open() throws SQLException {
        DBHelper = new DBHelper( context );
        db = DBHelper.getWritableDatabase();
        return this;
    }
    
    public void close() {
    	DBHelper.close();
    }
}
