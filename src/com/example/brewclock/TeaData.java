package com.example.brewclock;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class TeaData extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "teas.db";
	private static final int DATABASE_VERSION = 1;

	public static final String TABLE_NAME = "teas";

	public static final String _ID = BaseColumns._ID;
	public static final String NAME = "name";
	public static final String BREW_TIME = "brew_time";

	public TeaData(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/** Overrides **/

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// CREATE TABLE teas (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT
		// NULL, brew_time INTEGER);
		String sql = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + BREW_TIME + " INTEGER" + ");";
		db.execSQL(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	/**
	 * Insert a new tea record into the database.
	 * 
	 * @param name The name of the tea to add
	 * @param brewTime The time (in minutes) the tea should be brewed.
	 * @throws SQLException
	 */
	public void insert(String name, int brewTime) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(NAME, name);
		values.put(BREW_TIME, brewTime);

		db.insertOrThrow(TABLE_NAME, null, values);
	}

	/**
	 * Return a count of the number of teas in the database
	 * 
	 * @return long
	 */
	public long count() {
		SQLiteDatabase db = getReadableDatabase();
		return DatabaseUtils.queryNumEntries(db, TABLE_NAME);
	}

	/**
	 * Return all tea records in the database, ordered by name.
	 * 
	 * @param activity The Activity that will manage the cursor.
	 * @return {@link Cursor}
	 */
	public Cursor all(Activity activity) {
		String[] from = { _ID, NAME, BREW_TIME };
		String order = NAME;

		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, from, null, null, null, null, order);
		activity.startManagingCursor(cursor);

		return cursor;
	}
}