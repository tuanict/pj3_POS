/**
 * 
 */
package com.pj3.pos.res_public;

/**
 * @author L�C�ng
 *
 */
public class Menu {
	private int m_food_id;
	private String m_name;
	private int m_price;
	private String m_image;
	private Boolean m_status;
	
	
	
	public Menu(int m_food_id, String m_name, int m_price, String m_image,
			Boolean m_status) {
		super();
		this.m_food_id = m_food_id;
		this.m_name = m_name;
		this.m_price = m_price;
		this.m_image = m_image;
		this.m_status = m_status;
	}
	
	
	public Menu(String m_name, int m_price, String m_image, Boolean m_status) {
		super();
		this.m_name = m_name;
		this.m_price = m_price;
		this.m_image = m_image;
		this.m_status = m_status;
	}


	public int getM_food_id() {
		return m_food_id;
	}
	public void setM_food_id(int m_food_id) {
		this.m_food_id = m_food_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public int getM_price() {
		return m_price;
	}
	public void setM_price(int m_price) {
		this.m_price = m_price;
	}
	public String getM_image() {
		return m_image;
	}
	public void setM_image(String m_image) {
		this.m_image = m_image;
	}
	public Boolean getM_status() {
		return m_status;
	}
	public void setM_status(Boolean m_status) {
		this.m_status = m_status;
	}
}
