/**
 * 
 */
package com.pj3.pos.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author LÍCÙng
 *
 */
public class FoodStatisticHelper extends SQLiteOpenHelper{

	public static final String TABLE_FOODSTATISTIC = "FOODSTATISTIC";
	public static final String COLUMN_ID = "f_id";
	public static final String COLUMN_COUNT = "f_count";
	public static final String COLUMN_FBU = "fpu";
	public static final String COLUMN_F_B_ID = "f_b_id";
	public static final String COLUMN_F_M_ID = "f_m_id";
	
	public static final String DATABASE_CREATE = "create table"
	+ TABLE_FOODSTATISTIC + "("
	+ COLUMN_ID + "INT AUTO INCREAMENT NOT NULL, "
	+ COLUMN_COUNT + "INT NOT NULL, "
	+ COLUMN_F_B_ID + "INT NOT NULL, "
	+ COLUMN_F_M_ID + "INT NOT NULL,"
	+ COLUMN_F_M_ID + "INT NOT NULL, "
	+ "PRIMARY KEY (f_id), "
	+ "CONSTRAINT f_b_id FOREIGN KEY (f_b_id) REFERENCES BILL(b_id), "
	+ "CONSTRAINT f_m_id FOREIGN KEY (f_m_id) REFERENCES MENU(m_id)";
	
	private static final String DATABASE_NAME = "FOODSTATISTIC.db";
	private static final int  DATABASE_VERSION = 1; 
	
	public FoodStatisticHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
