/**
 * 
 */
package com.pj3.pos.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author LÍCÙng
 *
 */
public class EmployeeDataSource {
	private SQLiteDatabase database;
	private EmployeeHelper eHelper;
	
	public EmployeeDataSource(Context context){
		eHelper = new EmployeeHelper(context);
	}
	
	public void open() throws SQLException {
		database = eHelper.getWritableDatabase();
	}

	public void close() {
		eHelper.close();
	}
}
