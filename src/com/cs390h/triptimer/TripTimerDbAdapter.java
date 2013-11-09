package com.cs390h.triptimer;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * The TripTimerDbAdapter adds a layer between the SQLite database
 * and the TripTimerActivity so that data sent to the database or
 * data retrieved from the database does not have to be converted by
 * the TripTimerActivity class. 
 * 
 * @author andrew.canastar
 * @version 2013.11.02
 *
 */
public class TripTimerDbAdapter {
	
    // column labels
	public static final String KEY_ROWID = "_id";
    public static final String KEY_RNAME = "routename";
    public static final String KEY_TNAME = "tripname";
    public static final String TIME_NAME = "timeofday";
    public static final String TRIP_TIME = "triptime";
    public static final String TRIP_DATE = "tripdate";
    public static final String LAT = "lat";
    //public static final String STARTY = "starty";
    public static final String LONG = "long";
    //public static final String ENDY = "endy";
    
    // DbHelper is just for initializing the database and its tables
    private DbHelper DbHelper;
    private SQLiteDatabase db;
    // Every query or data request from the database returns a cursor
    // that can iterate over a set of data
    private Cursor cursor;
    
    // Constants and strings that are used to form SQL language queries of the database
    private static final String DATABASE_NAME = "trips";
	private static final String DATABASE_TABLE = "triphistory";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table triphistory"
			+ " (_id integer primary key ASC," +
			" tripname text not null," +
			" routename text not null," +
	        " timeofday text not null," +
	        " triptime integer not null," +
	        " tripdate text not null," +
	        " lat real not null," +
	        " long real not null);";
	private static final String TAG = "TripTimerDbAdapter";
	
	private final Context context;
	
	/**
	 * Database Helper (DbHelper) is an implementation of an SQLiteOpenHelper.
	 * A private inner class that regulates the initial steps of connecting to
	 * a database. Afterward, the DbAdapter routes data into SQL statements and
	 * parses result sets into data to be returned to the TripTimer activity.
	 * 
	 * @author andrew.canastar
	 * @version 2013.11.02
	 *
	 */
	private static class DbHelper extends SQLiteOpenHelper {

		/**
		 * Default Constructor creates a DbHelper in the context of the activity
		 * that called it.
		 * 
		 * @param context, Context of the Activity
		 */
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		/*
		 * (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);			
		}
		
		/*
		 * (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);			
		}
		
		public void removeTable( String table, SQLiteDatabase db ) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		}
		
		public void createTable( SQLiteDatabase db ) {
			db.execSQL(DATABASE_CREATE);
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
     * signal the failure.
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public TripTimerDbAdapter open() throws SQLException {
        DbHelper = new DbHelper( context );
        db = DbHelper.getWritableDatabase();
        return this;
    }
    
    /**
     * Closes the database and releases all resources.
     */
    public void close() {
    	DbHelper.close();
    }
    
    /**
     * Adds a new Route to the database via a ContentValue.
     * 
     * @param r, Route to be inserted as a new row.
     */
    public long insert( Route r ) {
    	// ContentValues is like a hash of column name constants to
    	// the intended values
    	ContentValues values = new ContentValues();
       	values.put( KEY_RNAME, r.getRouteName() );
       	values.put( KEY_TNAME, r.getTripName() );
       	values.put( TIME_NAME, r.getTimeOfDay() );
       	values.put( TRIP_DATE, r.getTripDate() );
       	values.put( TRIP_TIME, r.getTripTime() );
       	values.put( LAT, r.getLatitude() );
       	values.put( LONG, r.getLongitude() );
       	return db.insert( DATABASE_TABLE, null, values);
    }
    
    /**
     * Deletes a row. Not sure how to implement yet or whether to.
     */
    public void delete() {
    	
    }
    
    /**
     * Resets the database table.
     */
    public void reCreateTable() {
    	DbHelper.removeTable( DATABASE_TABLE, db );
    	DbHelper.createTable( db );
    }
    
    /**
     * Return a list of Route having all the database data.
     */
    public List<Route> getAllTrips() {
    	cursor = db.query( DATABASE_TABLE, new String[] {KEY_ROWID, KEY_RNAME,
    			KEY_TNAME, TIME_NAME, TRIP_TIME, TRIP_DATE, LAT, LONG},
        		null, null, null, null, null );
    	List<Route> r = resultSetToRouteList( cursor );
    	return r;
    }
    
    /**
     * Returns a list of all routes information for a particular trip.
     * Will take a trip name and return all its routes.
     */
    public List<Route> getAllRoutes() {
    	return null;
    }
    
    /**
     * Returns a list of Route with each route containing the average time
     * for a set of routes that share a common trip name.
     */
    public List<Route> getAverageTimeAllRoutes() {
    	return null;
    }
    
    /**
     * Returns a list of Route with each route containing the average time
     * for a set of routes that share a common trip and time of day.
     */
    public List<Route> getAverageTimeAllDays() {
    	return null;
    }
    
    /**
     * Returns a list of String containing the trip names.
     */
    public List<String> getAllTripNames() {
    	return null;
    }
    
    /**
     * Returns a list of String containing the route names
     */
    public List<String> getAllRouteNames() {
    	return null;
    }
    
    /**
     * Returns a List<Route> from a ResultSet, each ResultSet row
     * instantiates a Route.
     * 
     * @param rs
     * @return List<Route>
     */
    private List<Route> resultSetToRouteList( Cursor c ) {
    	List<Route> routes = new ArrayList<Route>();
    	if ( cursor != null && cursor.moveToFirst() ) {
    		do {
    			routes.add(new Route(
    					cursor.getString( 
    							cursor.getColumnIndex( 
    									this.getTripNameColumnName() ) ), 
						cursor.getString( 
    							cursor.getColumnIndex( 
    									this.getRouteNameColumnName() ) ),
						cursor.getString( 
    							cursor.getColumnIndex( 
    									this.getTimeOfDayColumnName() ) ),
						cursor.getString( 
    							cursor.getColumnIndex( 
    									this.getTripDateColumnName() ) ),
						cursor.getLong( 
    							cursor.getColumnIndex( 
    									this.getTripTimeColumnName() ) ),
						cursor.getDouble( 
    							cursor.getColumnIndex( 
    									this.getLatitudeColumnName() ) ),
						cursor.getDouble( 
    							cursor.getColumnIndex( 
    									this.getLongitudeColumnName() ) )				
    					) 
    				);
    		} while( cursor.moveToNext() );
    	}
    	return routes;
    }
    
    /**
     * Return a List<String> from a ResultSet, each ResultSet row
     * instantiates a String.
     * 
     * @param rs
     * @return List<String>
     */
    /*private List<String> resultSetToStringList( Cursor c ) {
    	return null;
    }*/
    
    /**
     * Returns the name of the ROWID column
     * 
     * @return String, KEY_ROWID
     */
    public String getPrimaryKeyColumnName() {
    	return KEY_ROWID;
    }
    
    /**
     * Returns the name of the tripName column
     * 
     * @return String, KEY_TNAME
     */
    public String getTripNameColumnName() {
    	return KEY_TNAME;
    			
    }
    
    /**
     * Returns the name of the routeName column
     * 
     * @return String, KEY_RNAME
     */
    public String getRouteNameColumnName() {
    	return KEY_RNAME; 
    }
    
    /**
     * Returns the name of the timeOfDay column
     * 
     * @return String, TIME_NAME
     */
    public String getTimeOfDayColumnName() {
    	return TIME_NAME; 
    }
    
    /**
     * Returns the name of the tripTime column
     * 
     * @return String, TRIP_TIME
     */
    public String getTripTimeColumnName() {
    	return TRIP_TIME; 
    }
    
    /**
     * Returns the name of the tripDate column
     * 
     * @return TRIP_DATE
     */
    public String getTripDateColumnName() {
    	return TRIP_DATE; 
    }
    
    /**
     * Returns the name of the tripTime column
     * 
     * @return String, LAT
     */
    public String getLatitudeColumnName() {
    	return LAT; 
    }
    
    /**
     * Returns the name of the tripDate column
     * 
     * @return LONG
     */
    public String getLongitudeColumnName() {
    	return LONG; 
    }
}

