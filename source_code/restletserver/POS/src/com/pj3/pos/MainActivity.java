package com.pj3.pos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;
import org.restlet.routing.VirtualHost;
import org.restlet.Component;
import org.restlet.Server;


import com.pj3.*;
import com.pj3.pos.res_public.*;
import com.pj3.pos.sqlite.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Component serverComponent = new Component();
		serverComponent.getServers().add(Protocol.HTTP, 8182);  
		final Router router = new Router(serverComponent.getContext().createChildContext()); 
		//router.attach("/api/users", Resource1.class);
		//router.attach("api/users/{uid}",Resource1.class);
		
		
		VirtualHost server = serverComponent.getDefaultHost();
		server.attach(router); 
		/*
		//init database and add default root user
		SQLiteDatabase mydb = SQLiteDatabase.openOrCreateDatabase("db1",null);
		mydb.execSQL("CREATE TABLE IF NOT EXISTS User(Username VARCHAR PRIMARY KEY, Password VARCHAR);");
		mydb.execSQL("INSERT OR IGNORE INTO User VALUES('admin','root')");
		mydb.close();
		*/
		
		try {
		serverComponent.start();
		} catch (Exception e) {e.printStackTrace();};
		System.out.println("this is sparta");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
