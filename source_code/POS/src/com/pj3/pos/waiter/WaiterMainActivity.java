package com.pj3.pos.waiter;

import java.util.ArrayList;
import java.util.List;

import com.pj3.pos.R;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.os.Build;

public class WaiterMainActivity extends Activity {

	public String[] numberOfTable = { "1", "2", "3", "4", "5", "6", "7", "8" }; // receive
																				// data
																				// from
																				// server
	public ArrayList<String> listOrderTempt = new ArrayList<String>();
	private ArrayList<String> listOrderDetailTempt = new ArrayList<String>();

	public ArrayAdapter<String> aaTable;
	public OrderAdapter aaOrder;
	private FoodAdapter aaFood;

	public Spinner spTable;
	public ListView lvOrder, lv_order_detail;
	private LinearLayout tab2_order, tab2_order_detail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.waiter_main);
		loadTab();
		init();
	}

	public void init() {
		spTable = (Spinner) findViewById(R.id.spTable);
		aaTable = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, numberOfTable);
		spTable.setAdapter(aaTable);

		lvOrder = (ListView) findViewById(R.id.lvOrder);
		listOrderTempt.add("Lượt 1");
		listOrderTempt.add("Lượt 2");
		aaOrder = new OrderAdapter(this, R.layout.waiter_item_order,
				listOrderTempt);
		lvOrder.setAdapter(aaOrder);

		tab2_order = (LinearLayout) findViewById(R.id.tab2_order);

		tab2_order_detail = (LinearLayout) findViewById(R.id.tab2_order_detail);
		tab2_order_detail.setVisibility(View.GONE);
		lv_order_detail = (ListView) findViewById(R.id.lv_order_detail);
		listOrderDetailTempt.add("Cafe đen");
		listOrderDetailTempt.add("Chanh leo");
		listOrderDetailTempt.add("Cafe sữa");
		listOrderDetailTempt.add("Mojito");
		aaFood = new FoodAdapter(this, R.layout.waiter_item_food,
				listOrderDetailTempt);
		lv_order_detail.setAdapter(aaFood);
	}

	public void loadTab() {
		final TabHost tabhost = (TabHost) findViewById(android.R.id.tabhost);

		tabhost.setup();
		TabHost.TabSpec spec;
		spec = tabhost.newTabSpec("spec1");
		spec.setContent(R.id.tab1);
//		spec.setIndicator("",
//				this.getResources().getDrawable(R.drawable.notification));
		tabhost.addTab(spec);

		spec = tabhost.newTabSpec("spec2");
		spec.setContent(R.id.tab2);
//		spec.setIndicator("", this.getResources().getDrawable(R.drawable.order));

		tabhost.addTab(spec);

		spec = tabhost.newTabSpec("spec3");
		spec.setContent(R.id.tab3);
//		spec.setIndicator("", this.getResources().getDrawable(R.drawable.bill));
		tabhost.addTab(spec);
		tabhost.setCurrentTab(1);
	}

	// event click button ThemMoi
	public void click_themmoi(View v) {
		tab2_order.setVisibility(View.GONE);
		tab2_order_detail.setVisibility(View.VISIBLE);
	}

	static class OrderHolder {
		TextView tvOrderName;

		public OrderHolder(View row) {
			tvOrderName = (TextView) row.findViewById(R.id.tv_order_name);
		}

		public void populateFrom(String s) {
			tvOrderName.setText(s);
		}
	}

	// for listview Order, "String" is temporary input
	class OrderAdapter extends ArrayAdapter<String> {
		Activity context;
		int idLayout;
		ArrayList<String> listOrder;

		public OrderAdapter(Activity context, int resource,
				ArrayList<String> listOrder) {
			super(context, resource, listOrder);
			this.context = context;
			this.idLayout = resource;
			this.listOrder = listOrder;
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			OrderHolder holder;
			if (row == null) {
				row = context.getLayoutInflater().inflate(idLayout, parent,
						false);
				holder = new OrderHolder(row);
				row.setTag(holder);
			} else {
				holder = (OrderHolder) row.getTag();
			}
			holder.populateFrom(listOrder.get(position));
			return row;
		}
	}

	static class FoodHolder {
		ImageView ivFoodIcon;
		TextView tvFoodName;
		Spinner spFoodNumber;

		public FoodHolder(View row) {
			ivFoodIcon = (ImageView) row.findViewById(R.id.iv_food_icon);
			tvFoodName = (TextView) row.findViewById(R.id.tv_food_name);
			spFoodNumber = (Spinner) row.findViewById(R.id.sp_food_number);
		}

		public void populateFrom(String s) {
			tvFoodName.setText(s);
		}
	}

	// for listview order_detail, "String" is temporary input
	class FoodAdapter extends ArrayAdapter<String> {
		Activity context;
		int idLayout;
		ArrayList<String> data;

		// private String[] data_spin = { "1", "2", "3", "4", "5", "6", "7",
		// "8",
		// "9", "10", "11", "12" };
		// private ArrayAdapter<String> aa_spin = new ArrayAdapter<String>(
		// WaiterMainActivity.this, android.R.layout.simple_list_item_1,
		// data_spin);

		public FoodAdapter(Activity context, int idLayout,
				ArrayList<String> data) {
			super(context, idLayout, data);
			this.context = context;
			this.idLayout = idLayout;
			this.data = data;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			FoodHolder holder;
			if (row == null) {
				row = context.getLayoutInflater().inflate(idLayout, parent,
						false);
				holder = new FoodHolder(row);
				row.setTag(holder);
			} else {
				holder = (FoodHolder) row.getTag();
			}
			holder.populateFrom(data.get(position));
			return row;
		}
	}
}
