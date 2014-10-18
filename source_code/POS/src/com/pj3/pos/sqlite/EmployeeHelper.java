/**
 * 
 */
package com.pj3.pos.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author LÍCÙng
 *
 */
public class EmployeeHelper extends SQLiteOpenHelper {

	public static final String TABLE_EMPLOYEE = "EMPLOYEE";
	public static final String COLUMN_ID = "e_id";
	public static final String COLUMN_NAME = "e_name";
	public static final String COLUMN_EMAIL = "e_email";
	public static final String COLUMN_PASS = "e_pass";
	public static final String COLUMN_IMAGE = "e_image";
	public static final String COLUMN_PHONE = "e_phone_number";
	public static final String COLUMN_POSITION = "e_POSITION_p_id";
	
	public static final String DATABASE_CREATE = "create table"
			+ TABLE_EMPLOYEE
			+ "("
			+ COLUMN_ID
			+ " integer primary key autoincrement, "
			+ COLUMN_NAME
			+ "VARCHAR(45) NOT NULL, "
			+ COLUMN_EMAIL
			+ "VARCHAR(20) NOT NULL, "
			+ COLUMN_PASS
			+ "VARCHAR(45) NOT NULL, "
			+ COLUMN_IMAGE
			+ "VARCHAR(45) NOT NULL, "
			+ COLUMN_PHONE
			+ "INT NOTNULL, "
			+ COLUMN_POSITION
			+ "INT NOT NULL, "
			+ "CONSTRAINT e_POSITION_p_id FOREIGN KEY (e_POSITION_p_id) REFERENCES POSITION(p_id)";
	
	private static final String DATABASE_NAME = "employee.db";
	  private static final int  DATABASE_VERSION = 1;
	
	public EmployeeHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(DATABASE_CREATE);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		Log.w(EmployeeHelper.class.getName(),
		        "Upgrading database from version " + arg1 + " to "
		            + arg2 + ", which will destroy all old data");
		    arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
		    onCreate(arg0);

	}

}
