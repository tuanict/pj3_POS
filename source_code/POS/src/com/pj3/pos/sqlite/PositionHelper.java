/**
 * 
 */
package com.pj3.pos.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author LÍCÙng
 *
 */
public class PositionHelper extends SQLiteOpenHelper{
	public static final String TABLE_POSITION = "POSITION";
	public static final String COLUMN_ID = "p_id";
	public static final String COLUMN_NAME = "p_name";
	public static final String COLUMN_SALARY = "p_salary";
	
	public static final String DATABASE_CREATE = "create table"
			+ TABLE_POSITION + "(" 
			+ COLUMN_ID + "INT NOT NULL, "
			+ COLUMN_NAME + "VARCHAR(45) NOT NULL, "
			+ COLUMN_SALARY + "INT NOT NULL, "
			+ "PRIMARY KEY (p_id)";
	private static final String DATABASE_NAME = "position.db";
	private static final int  DATABASE_VERSION = 1;
	public PositionHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
