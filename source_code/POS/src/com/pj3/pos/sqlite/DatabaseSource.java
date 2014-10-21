/**
 * 
 */
package com.pj3.pos.sqlite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pj3.pos.res_public.Bill;
import com.pj3.pos.res_public.Employee;
import com.pj3.pos.res_public.Food;
import com.pj3.pos.res_public.Order;

/**
 * @author LÍCÙng
 *
 */
public class DatabaseSource implements SqliteAPIs{
	DatabaseHelper dHelper;
	File billTem;
	public static int billTempId = 0;
	public static final String FILENAME = "BillTemp.json";
	public DatabaseSource(Context context){
		dHelper = new DatabaseHelper(context);
		
		//Create file tamporary
		String[] files = context.getFilesDir().list();
		boolean ok = true;
		for(String file : files){
			if(file.equals(FILENAME)){
				ok = false;
				break;
			}
		}
		
		if(ok) billTem = new File(context.getFilesDir(), FILENAME);
			
		
	}
	@Override
	public int createUser(Employee user) {
		//Check email exits
		List<Employee> employees = this.getAllUsers();
		int size = employees.size();
		int count = 0;
		while(count < size){
			if (employees.get(count).getE_email().equals(user.getE_email()))
				return -1;
			else count++;
		}
		
		SQLiteDatabase db = dHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(dHelper.COLUMN_E_NAME,user.getE_name());
		values.put(dHelper.COLUMN_E_EMAIL, user.getE_email());
		values.put(dHelper.COLUMN_E_PASS, user.getE_pass());
		values.put(dHelper.COLUMN_E_IMAGE, user.getE_image());
		values.put(dHelper.COLUMN_E_PHONE, user.getE_phone_number());
		values.put(dHelper.COLUMN_E_POSITION, user.getPOSITION_p_id());
		
		//insert row
		int userId = (int) db.insert(dHelper.TABLE_EMPLOYEE, null, values);
		return userId;
	}

	@Override
	public Employee getUser(int userId) {
		SQLiteDatabase db = dHelper.getReadableDatabase();
		String query = "SELECT * FROM " + dHelper.TABLE_EMPLOYEE
				+ "WHERE" + dHelper.COLUMN_E_ID + " = " + userId;
		
		Log.e(dHelper.LOG, query);
		Cursor c = db.rawQuery(query, null);
		
		if(c != null)
			c.moveToFirst();
		
		Employee user = new Employee();
		user.setE_id(c.getInt(c.getColumnIndex(dHelper.COLUMN_E_ID)));
		user.setE_name(c.getString(c
				.getColumnIndex(dHelper.COLUMN_E_NAME)));
		user.setE_email(c.getString(c.getColumnIndex(dHelper.COLUMN_E_EMAIL)));
		user.setE_pass(c.getString(c.getColumnIndex(dHelper.COLUMN_E_PASS)));
		user.setE_image(c.getString(c.getColumnIndex(dHelper.COLUMN_E_IMAGE)));
		user.setE_phone_number(c.getInt(c.getColumnIndex(dHelper.COLUMN_E_PHONE)));
		user.setPOSITION_p_id(c.getInt(c.getColumnIndex(dHelper.COLUMN_E_POSITION)));
		
		return user;
	}

	@Override
	public List<Employee> getAllUsers() {
		List<Employee> employees = new ArrayList<Employee>();
		String query = "SELECT * FROM " + dHelper.TABLE_EMPLOYEE;
		Log.e(dHelper.LOG, query);
		
		SQLiteDatabase db = dHelper.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		
		//looping through all rows and adding to list
		if(c.moveToFirst()){
			do{
				Employee user = new Employee();
				user.setE_id(c.getInt(c.getColumnIndex(dHelper.COLUMN_E_ID)));
				user.setE_name(c.getString(c
						.getColumnIndex(dHelper.COLUMN_E_NAME)));
				user.setE_email(c.getString(c.getColumnIndex(dHelper.COLUMN_E_EMAIL)));
				user.setE_pass(c.getString(c.getColumnIndex(dHelper.COLUMN_E_PASS)));
				user.setE_image(c.getString(c.getColumnIndex(dHelper.COLUMN_E_IMAGE)));
				user.setE_phone_number(c.getInt(c.getColumnIndex(dHelper.COLUMN_E_PHONE)));
				user.setPOSITION_p_id(c.getInt(c.getColumnIndex(dHelper.COLUMN_E_POSITION)));
				
				employees.add(user);
			}while(c.moveToNext());
		}
		return employees;
	}

	@Override
	public List<Employee> getUserByPosition(int postionId) {
		List<Employee> employees = new ArrayList<Employee>();
		String query = "SELECT * FROM " + dHelper.TABLE_EMPLOYEE 
				+ "WHERE" + dHelper.COLUMN_E_POSITION + " = " +postionId;
		Log.e(dHelper.LOG, query);
		
		SQLiteDatabase db = dHelper.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		
		//looping through all rows and adding to list
		if(c.moveToFirst()){
			do{
				Employee user = new Employee();
				user.setE_id(c.getInt(c.getColumnIndex(dHelper.COLUMN_E_ID)));
				user.setE_name(c.getString(c
						.getColumnIndex(dHelper.COLUMN_E_NAME)));
				user.setE_email(c.getString(c.getColumnIndex(dHelper.COLUMN_E_EMAIL)));
				user.setE_pass(c.getString(c.getColumnIndex(dHelper.COLUMN_E_PASS)));
				user.setE_image(c.getString(c.getColumnIndex(dHelper.COLUMN_E_IMAGE)));
				user.setE_phone_number(c.getInt(c.getColumnIndex(dHelper.COLUMN_E_PHONE)));
				user.setPOSITION_p_id(c.getInt(c.getColumnIndex(dHelper.COLUMN_E_POSITION)));
				
				employees.add(user);
			}while(c.moveToNext());
		}
		return employees;
	}

	@Override
	public boolean updateUser(Employee user) {
		SQLiteDatabase db = dHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		boolean ok = false;
		
		values.put(dHelper.COLUMN_E_NAME,user.getE_name());
		values.put(dHelper.COLUMN_E_EMAIL, user.getE_email());
		values.put(dHelper.COLUMN_E_PASS, user.getE_pass());
		values.put(dHelper.COLUMN_E_IMAGE, user.getE_image());
		values.put(dHelper.COLUMN_E_PHONE, user.getE_phone_number());
		values.put(dHelper.COLUMN_E_POSITION, user.getPOSITION_p_id());
		try{
			int result = db.update(dHelper.TABLE_EMPLOYEE, values,
					dHelper.COLUMN_E_ID + " = ?",
					new String[] { String.valueOf(user.getE_id()) });
			ok = true;
		}catch(Exception e){
			ok = false;
		}
		return ok;
	}

	@Override
	public boolean checkUser(String username, String password) {
		List<Employee> employees = this.getAllUsers();
		int size = employees.size();
		int count = 0;
		while(count < size){
			if (employees.get(count).getE_email().equals(username)
					&& employees.get(count).getE_pass().equals(password))
				return true;
			else count++;
		}
		return false;
	}

	@Override
	public boolean deleteUser(int userId) {
		SQLiteDatabase db = dHelper.getWritableDatabase();
		boolean ok = false;
		try{
			db.delete(dHelper.TABLE_EMPLOYEE, dHelper.COLUMN_E_ID + " = ?",
					new String[] { String.valueOf(userId) });
			ok = true;
		}catch(Exception e){
			ok = false;
		}
		return ok;
	}

	@Override
	public int createFood(Food menu) {
		List<Food> menus = this.getAllFood();
		int size = menus.size();
		int count = 0;
		while(count < size){
			if(menus.get(count).getM_name().equals(menu.getM_name()))
				return -1;
		}
		
		SQLiteDatabase db = dHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(dHelper.COLUMN_M_IMAGE, menu.getM_image());
		values.put(dHelper.COLUMN_M_NAME, menu.getM_name());
		values.put(dHelper.COLUMN_M_PRICE, menu.getM_price());
		values.put(dHelper.COLUMN_M_STATUS, menu.getM_status());
		
		//insert row
		int menuId = (int) db.insert(dHelper.TABLE_MENU, null, values);
		return menuId;
	}

	@Override
	public boolean updateMenu(Food menu) {
		SQLiteDatabase db = dHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(dHelper.COLUMN_M_IMAGE, menu.getM_image());
		values.put(dHelper.COLUMN_M_NAME, menu.getM_name());
		values.put(dHelper.COLUMN_M_PRICE, menu.getM_price());
		values.put(dHelper.COLUMN_M_STATUS, menu.getM_status());
		
		try{
			db.update(dHelper.TABLE_MENU, values,
					dHelper.COLUMN_M_ID + " = ? ",
					new String[] { String.valueOf(menu.getM_food_id()) });
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean changeStatusFood(int foodId, boolean status) {
		Food menu = getFood(foodId);
		if(menu == null)
			return false;
		menu.setM_status(status);
		return this.updateMenu(menu);
	}

	@Override
	public Food getFood(int foodId) {
		SQLiteDatabase db = dHelper.getReadableDatabase();
		String query = "SELECT * FROM " + dHelper.TABLE_MENU + "WHERE "
				+ dHelper.COLUMN_M_ID + " = " + foodId;
		Log.e(dHelper.LOG, query);
		
		Cursor c = db.rawQuery(query, null);
		if(c != null)
			c.moveToFirst();
		
		Food menu = new Food();
		menu.setM_food_id(c.getInt(c.getColumnIndex(dHelper.COLUMN_M_ID)));
		menu.setM_image(c.getString(c.getColumnIndex(dHelper.COLUMN_M_IMAGE)));
		menu.setM_name(c.getString(c.getColumnIndex(dHelper.COLUMN_M_NAME)));
		menu.setM_price(c.getInt(c.getColumnIndex(dHelper.COLUMN_M_PRICE)));
		menu.setM_status(c.getInt(c.getColumnIndex(dHelper.COLUMN_M_STATUS)) == 1);
		
		return menu;
	}

	@Override
	public List<Food> getAllFood() {
		List<Food> menus = new ArrayList<Food>();
		String query = "SELECT * FROM " + dHelper.TABLE_MENU;
		Log.e(dHelper.LOG,query);
		
		SQLiteDatabase db = dHelper.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		
		// looping through all rows and adding to list
		if(c.moveToFirst()){
			do{
				Food menu = new Food();
				menu.setM_food_id(c.getInt(c.getColumnIndex(dHelper.COLUMN_M_ID)));
				menu.setM_image(c.getString(c.getColumnIndex(dHelper.COLUMN_M_IMAGE)));
				menu.setM_name(c.getString(c.getColumnIndex(dHelper.COLUMN_M_NAME)));
				menu.setM_price(c.getInt(c.getColumnIndex(dHelper.COLUMN_M_PRICE)));
				menu.setM_status(c.getInt(c.getColumnIndex(dHelper.COLUMN_M_STATUS)) == 1);
				
				menus.add(menu);
			}while(c.moveToNext());
		}
		return menus;
	}

	@Override
	public int createBill(Bill bill) {
		
		return 0;
	}

	@Override
	public Bill getBill(int billId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Bill> getAllBill() {
		List<Bill> bills = new ArrayList<Bill>();
		
		SQLiteDatabase db = dHelper.getReadableDatabase();
		String query = "SELECT * FROM " + dHelper.TABLE_BILL;
		Log.e(dHelper.LOG, query);
		Cursor c = db.rawQuery(query, null);
		
		//looping through all rows and adding to list
		if(c.moveToFirst()){
			do{
				Bill bill = new Bill();
				bill.setB_id(c.getInt(c.getColumnIndex(dHelper.COLUMN_B_ID)));
				bill.setB_count(c.getInt(c.getColumnIndex(dHelper.COLUMN_B_COUNT)));
//				bill.setB_time_stamp(c.getString(c.getColumnIndexOrThrow(dHelper.COLUMN_B_TIME_STAMP)));
				
				bills.add(bill);
			}while(c.moveToNext());
		}
		return bills;
	}

	@Override
	public int createBillTemp(Order order) {
		order.setOrderId(billTempId);
		
		return 0;
	}

	@Override
	public boolean updateBillTemp(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBillTemp(int billId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order getBillTemp(int billId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
