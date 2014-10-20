/**
 * 
 */
package com.pj3.pos.sqlite;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.pj3.pos.res_public.Bill;
import com.pj3.pos.res_public.Employee;
import com.pj3.pos.res_public.Menu;

/**
 * @author LÍCÙng
 *
 */
public class DatabaseSource implements SqliteAPIs{
	DatabaseHelper databaseHelper;
	
	public DatabaseSource(Context context){
		databaseHelper = new DatabaseHelper(context);
	}
	@Override
	public int createUser(Employee user) {
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		
		return 0;
	}

	@Override
	public Employee getUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getUserByPosition(int postionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(Employee user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkUser(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int createFood(Menu menu) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeStatusFood(int foodId, boolean status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Menu getFood(int foodId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getAllFood() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createBill(Bill bill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Bill getBill(int billId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createBillTemp(Bill bill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateBillTemp(Bill bill) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBillTemp(int billId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bill getBillTemp(int billId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
