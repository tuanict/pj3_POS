/**
 * 
 */
package com.pj3.pos.sqlite;

import java.util.List;

import com.pj3.pos.res_public.Bill;
import com.pj3.pos.res_public.Employee;
import com.pj3.pos.res_public.Menu;

/**
 * @author LÍCÙng
 *
 */
public interface SqliteAPIs {
	//Users actions
	/**
	 * Create user
	 * @user user will be created
	 * @return id of user is created
	 */
	public int createUser(Employee user);
	
	/**
	 * get user by userId
	 * @param userId
	 * @return user has id: userId
	 */
	public Employee getUser(int userId);
	
	/**
	 * Get all user on server
	 * @return all employee 
	 */
	public List<Employee> getAllUsers();
	
	/**
	 * get Employee by positon
	 * @param postionId
	 * @return list employee 
	 */
	public List<Employee> getUserByPosition(int postionId);
	
	/**
	 * update user on server
	 * @param user
	 * @return true if success of false if fail
	 */
	public boolean updateUser(Employee user);
	
	/**
	 * Check user login 
	 * @param username
	 * @param password
	 * @return true if success or false if fail
	 */
	public boolean checkUser(String username, String password);
	
	/**
	 * Delete user by userId
	 * @param userId
	 * @return true if success or false if fail
	 */
	public boolean deleteUser(int userId);
	
	
	//MENU ACTION
	/**
	 * Create new food in menu
	 * @param menu
	 * @return id of new food
	 */
	public int createFood(Menu menu);
	
	/**
	 * Update menu 
	 * @param menu
	 * @return true if success or false if fail
	 */
	public  boolean updateMenu(Menu menu);
	
	/**
	 * Change status of food
	 * @param foodId
	 * @param status true: enable or false: disable
	 * @return true if success or false if fail
	 */
	public boolean changeStatusFood(int foodId, boolean status);
	
	/**
	 * Get food by food Id
	 * @param foodId
	 * @return return Food
	 */
	public Menu getFood(int foodId);
	
	/**
	 * Get all food in menu
	 * @return list food
	 */
	public List<Menu> getAllFood();
	
	//BILL ACTION 
	/**
	 * Create bill
	 * @param bill
	 * @return id of new bill
	 */
	public int createBill(Bill bill);
	
	/**
	 * get bill info
	 * @param billId
	 * @return bill
	 */
	public Bill getBill(int billId);
	
	//BILL ACTION TEMPORARY
	/**
	 * Create bill temporary
	 * @param bill
	 * @return id of new bill
	 */
	public int createBillTemp(Bill bill);
	
	/**
	 * Update bill temporary
	 * @param bill
	 * @return true if success or false if fail
	 */
	public boolean updateBillTemp(Bill bill);
	
	/**
	 * Delete bill temporary
	 * @param billId
	 * @return true if success or false if fail
	 */
	public boolean deleteBillTemp(int billId);
	
	/**
	 * Get bill temporary
	 * @param billId
	 * @return bill temporary 
	 */
	public Bill getBillTemp(int billId);
	
}
