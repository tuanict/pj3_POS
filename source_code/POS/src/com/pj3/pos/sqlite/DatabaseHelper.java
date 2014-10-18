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
public class DatabaseHelper extends SQLiteOpenHelper {
	//Logcat tag 
	public static final String LOG = "DatabaseHelper";
	
	//Database name
	public static final String DATABASE_NAME = "POS";
	
	//Database version
	public static final int DATABASE_VERSION = 1;
	
	//Tables name
	public static final String TABLE_EMPLOYEE = "EMPLOYEE";
	public static final String TABLE_POSITION = "POSITION";
	public static final String TABLE_FOODSTATISTIC = "FOODSTATTISTIC";
	public static final String TABLE_BILL = "BILL";
	public static final String TABLE_MENU = "MENU";
	
	//Employee table - column name
	public static final String COLUMN_E_ID = "e_id";
	public static final String COLUMN_E_NAME = "e_name";
	public static final String COLUMN_E_EMAIL = "e_email";
	public static final String COLUMN_E_PASS = "e_pass";
	public static final String COLUMN_E_IMAGE = "e_image";
	public static final String COLUMN_E_PHONE = "e_phone_number";
	public static final String COLUMN_E_POSITION = "e_POSITION_p_id";
	
	//Position table - column names
	public static final String COLUMN_P_ID = "p_id";
	public static final String COLUMN_P_NAME = "p_name";
	public static final String COLUMN_P_SALARY = "p_salary";
	
	//FoodStistic - column names
	public static final String COLUMN_F_ID = "f_id";
	public static final String COLUMN_F_COUNT = "f_count";
	public static final String COLUMN_FBU = "fpu";
	public static final String COLUMN_F_B_ID = "f_b_id";
	public static final String COLUMN_F_M_ID = "f_m_id";
	
	//Bill - column names
	public static final String COLUMN_B_ID = "b_id";
	public static final String COLUMN_B_COUNT = "b_count";
	public static final String COLUMN_B_TIME_STAMP = "b_time_stamp";
	
	//Menu - column names
	public static final String COLUMN_M_ID = "m_food_id";
	public static final String COLUMN_M_NAME = "m_name";
	public static final String COLUMN_M_PRICE = "m_price";
	public static final String COLUMN_M_IMAGE = "m_image";
	public static final String COLUMN_M_STATUS = "m_status";
	
	// Table Create Statements
	//Employee table create statement
	public static final String DATABASE_E_CREATE = "create table"
			+ TABLE_EMPLOYEE
			+ "("
			+ COLUMN_E_ID
			+ " INT primary key autoincrement, "
			+ COLUMN_E_NAME
			+ "VARCHAR(45) NOT NULL, "
			+ COLUMN_E_EMAIL
			+ "VARCHAR(20) NOT NULL, "
			+ COLUMN_E_PASS
			+ "VARCHAR(45) NOT NULL, "
			+ COLUMN_E_IMAGE
			+ "VARCHAR(45) NOT NULL, "
			+ COLUMN_E_PHONE
			+ "INT NOTNULL, "
			+ COLUMN_E_POSITION
			+ "INT NOT NULL, "
			+ "CONSTRAINT e_POSITION_p_id FOREIGN KEY (e_POSITION_p_id) REFERENCES POSITION(p_id)";
	
	//POSITION table create statement
	public static final String DATABASE_P_CREATE = "create table"
			+ TABLE_POSITION + "(" 
			+ COLUMN_P_ID + "INT NOT NULL, "
			+ COLUMN_P_NAME + "VARCHAR(45) NOT NULL, "
			+ COLUMN_P_SALARY + "INT NOT NULL, "
			+ "PRIMARY KEY (p_id)";
	
	//FoodStatis
	public static final String DATABASE_CREATE = "create table"
			+ TABLE_FOODSTATISTIC + "("
			+ COLUMN_F_ID + "INT AUTO INCREAMENT NOT NULL, "
			+ COLUMN_F_COUNT + "INT NOT NULL, "
			+ COLUMN_F_B_ID + "INT NOT NULL, "
			+ COLUMN_F_M_ID + "INT NOT NULL,"
			+ COLUMN_F_M_ID + "INT NOT NULL, "
			+ "PRIMARY KEY (f_id), "
			+ "CONSTRAINT f_b_id FOREIGN KEY (f_b_id) REFERENCES BILL(b_id), "
			+ "CONSTRAINT f_m_id FOREIGN KEY (f_m_id) REFERENCES MENU(m_id)";
	
	
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
