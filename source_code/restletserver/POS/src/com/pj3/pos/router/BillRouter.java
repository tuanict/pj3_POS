package com.pj3.pos.router;
import java.io.IOException;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.*;
import org.restlet.ext.json.JsonRepresentation;
import android.content.Context;

import com.pj3.*;
import com.pj3.pos.res_public.*;
import com.pj3.pos.sqlite.*;

import com.pj3.pos.sqlite.DatabaseSource;

public class BillRouter extends ServerResource {
	DatabaseSource db = new DatabaseSource(null);
	@Get
	public Representation doGet (Representation entity){
		try {
		String tmp = getReference().getRemainingPart();
		String[] parts = tmp.split("/");
		String uidString = parts[1];
		Bill b = db.getBill(Integer.parseInt(uidString));
		JSONObject jo = new JSONObject();
		jo.put("bid", b.getB_id());
		JSONArray ja = new JSONArray();
		
		List<FoodStatistic>listFood = db.getCookingOrder(Integer.parseInt(uidString));
		for(FoodStatistic f: listFood){
			JSONObject jo1 = new JSONObject();
			jo1.put("fid", Integer.toString(f.getF_b_id()));
			jo1.put("fcount", Integer.toString(f.getF_count()));
			jo1.put("fpu", Integer.toString(f.getFpu()));
			ja.put(jo1);
			
		}
		jo.put("foodarray", ja);
		return new JsonRepresentation(jo);
		
		} catch(Exception e){
			e.printStackTrace();
			return new JsonRepresentation("{\"message\":\"error\"}");
		}
		
	}
	
	
	@Post("json")
	public Representation doPost (Representation entity) {
		try{
			JsonRepresentation  jsonRep  = new JsonRepresentation(entity);
			JSONObject 			jsonObj  = jsonRep.getJsonObject();
			JSONArray 			foodarray = jsonObj.getJSONArray("foodarray");
			int total  =0;
			for(int i=0; i < foodarray.length(); i ++){
				JSONObject jo = foodarray.getJSONObject(i);
				Food f = db.getFood(Integer.parseInt(jo.getString("fid")));
				int price = f.getM_price();
				int count = Integer.parseInt(jo.getString("count"));
				total += price*count;
			}
			Bill x = new Bill(total, new Date());
			db.createBill(x);
			
		} catch(IOException e){
			return new JsonRepresentation("{\"message\":\"error\"}");
		}
		catch (JSONException e2){
			return new JsonRepresentation("{\"message\":\"error\"}");
		}
		return new JsonRepresentation("{\"message\":\"success\"}");
	}
	
	
}
