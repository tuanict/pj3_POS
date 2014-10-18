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
public class PositionDataSource {
	private SQLiteDatabase database;
	private PositionHelper pHelper;
	
	public PositionDataSource(Context context){
		pHelper = new PositionHelper(context);
	}
	
	public void open() throws SQLException {
		database = pHelper.getWritableDatabase();
	}

	public void close() {
		pHelper.close();
	}
}
