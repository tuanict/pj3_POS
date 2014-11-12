package com.pj3.pos.bep;
import android.R;
import android.R.string;
import android.app.ActionBar.LayoutParams;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.pj3.pos.R.layout;

public class BepActivity extends ListActivity {	

    Button btn;                                        //button close/opend scrollview
    ArrayList<HashMap<String, Object>> foods;          //data of listview
    String _name[];                                    //food name
    String _pro[];                                     //food property
    String _sl[];                                      //food number
    Integer _photo[];                                  //food image
    int count = 0;                                     // bien tam 
	LayoutInflater inflater;
	View layoutoflist;                                 // layout of list view
	View layoutofscroll;                               //layout of scrollview
	boolean close = true;                              //state of btn	
	boolean lammo = false;                             //state of list item
	boolean taomoi_scroll = false;                     //state of scroll item
	public  CustomAdapter adapter;                     //adapter of list
	int x;                                             //save position of list item 	  
	public	Handler mHandler;         
	public     Runnable _run;                          //runable when list item delay

    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setContentView(com.pj3.pos.R.layout.bep_activity);
      
        layoutoflist = findViewById(com.pj3.pos.R.id.mot);
        layoutofscroll= findViewById(com.pj3.pos.R.id.scrollview);
        _name = new String[100];
        _pro = new String[100];
        _sl = new String[100];
        _photo = new Integer[100];
        foods=new ArrayList<HashMap<String,Object>>();
        String names[]={"Cafe","Tra chanh","Cafe den","Coca","Kem","Pepsi","Tra sua","Sua chua"}; 
        String pro[]={"id","nd","nd","id","nd","nd","nd","nd"};
        String sl[]={"x8","x8","x8","x8","x8","x8","x8","x8"};
        Integer[] photos={com.pj3.pos.R.drawable.f1,com.pj3.pos.R.drawable.f2, com.pj3.pos.R.drawable.f3,com.pj3.pos.R.drawable.f4, com.pj3.pos.R.drawable.f5,com.pj3.pos.R.drawable.f6, com.pj3.pos.R.drawable.f7,com.pj3.pos.R.drawable.f9};              
        HashMap<String , Object> temp;        	 
        int noOfFoods=names.length;        	
        for(int i=0;i<noOfFoods;i++)
        {
        	  temp=new HashMap<String, Object>();        	 
        	  temp.put("name", names[i]);
        	  temp.put("pro", pro[i]);    
        	  temp.put("sl", sl[i]);    
        	  temp.put("photo", photos[i]);        	  
        	  foods.add(temp);        
         }
        adapter=new CustomAdapter(this, com.pj3.pos.R.layout.bep_list_item,foods); 
        setListAdapter(adapter); 
       
        //**tao doi tuong  SwipeDismissListViewTouchListener de sau do gan cho listView
        ListView listView = getListView();        
        SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener(listView,
        //**tao moi lai ham Dismisscallback cua doi tuong SwipeDismissListViewTouchListener
        new SwipeDismissListViewTouchListener.DismissCallbacks() {
        	@Override
        	public boolean canDismiss(int position) { return true;}                            
            @Override
            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
            	//*xet su kien cho moi doi tuong duoc vuot
            	for (int position : reverseSortedPositions) {                               	
               	//**luu lai du lieu cua list item tai vi tri position
            		HashMap<String , Object> temp = adapter.getItem(position);//                                	
                    _name[count] = (String)temp.get("name");
                    _pro[count] = (String)temp.get("pro");
                    _sl[count] = (String)temp.get("sl");
                    _photo[count] = (Integer)temp.get("photo");
                    count++;
                    //**end luu lai du lieu cua list item tai vi tri position
                    //**tao doi tuong button tuong ung ben scrollview
                    final ViewGroup dismissableContainer = (ViewGroup) findViewById(com.pj3.pos.R.id.dismissable_container);
                    final Button dismissableButton = new Button(getApplicationContext());            
                    dismissableButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    dismissableButton.setText("1pt "+ _name[count-1]+" "+_sl[count-1]);
                    dismissableButton.setOnClickListener(new View.OnClickListener() {
                    	@Override
                        public void onClick(View view) { Toast.makeText(BepActivity.this, "Clicked " + ((Button) view).getText(), Toast.LENGTH_SHORT).show();}
                    });
                    // **bat su kien vuot cua button trong scroll
                     dismissableButton.setOnTouchListener(new SwipeDismissTouchListener( dismissableButton, null,
                     new SwipeDismissTouchListener.DismissCallbacks() {
                    	 @Override
                         public boolean canDismiss(Object token) { return true;}
                         @Override
                         public void onDismiss(View view, Object token) {
                        	 dismissableContainer.removeView(dismissableButton);    
                        	 //them listitem
                        	 HashMap<String , Object> _temp; 
                        	 _temp=new HashMap<String, Object>();        	 
                        	 _temp.put("name", "jsdfh");
                        	 _temp.put("pro", "kjhvx");    
                        	 _temp.put("sl", "ckjsd");    
                        	 _temp.put("photo", com.pj3.pos.R.drawable.f1);        	  
                        	 foods.add(_temp);    
                        	 
                        	 //end them list item
                         }
                    }));//**end bat su kien vuot cua button trong scroll
                    dismissableContainer.addView(dismissableButton);  //them button vao view?                               
                    //**end tao doi tuong button tuong ung ben scrollview
                    //**tao animation cho doi tuong vua vuot
                    AlphaAnimation animation = new AlphaAnimation(0.3f, 0.2f);                                    	    
                    animation.setDuration(9000);
                    animation.setFillAfter(false);                                	
                    x = (int)position;
                    listView.getChildAt(position).startAnimation(animation);  //gan animation                                    
                   	lammo = true;
                    mHandler = new Handler();
                    _run = new Runnable() {
                    	@Override
						public void run() {
							adapter.remove(adapter.getItem(x)); 
                            adapter.notifyDataSetChanged();
                            taomoi_scroll = true;
                            lammo = false;
						}
                    };
                    mHandler.postDelayed(_run, 9000);
                   //**end tao animation cho duoi tuong vua vuot 
               }//ket thuc ham for de xet su kien cho moi duoi tuong khi vuot
           }  //**end overide lai ham onDismiss
       }); //**end tao doi tuong  SwipeDismissListViewTouchListener de bat su kien trong listView       
      listView.setOnTouchListener(touchListener);       
      listView.setOnScrollListener(touchListener.makeScrollListener());
      
      btn = (Button) findViewById(R.id.button1);
      findViewById(com.pj3.pos.R.id.scrollview).setVisibility(View.GONE);   
      btn.setOnClickListener(new View.OnClickListener() { 
           @Override 
           public void onClick(View arg0) { 
        	   if(close == true){
        		   findViewById(com.pj3.pos.R.id.scrollview).setVisibility(View.VISIBLE);
        		   layoutoflist.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.6f));
          		   layoutofscroll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.4f));
        		   close = false;        		 
        		   AlphaAnimation animation = new AlphaAnimation(1, 0.2f);           	 
        		   animation.setFillAfter(false); 
        		   btn.startAnimation(animation);
        		   btn.setAnimation(animation);           	
        	   }
        	   else if(close == false){
        		   layoutoflist.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        		   layoutofscroll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0f));
        		   findViewById(com.pj3.pos.R.id.scrollview).setVisibility(View.GONE);
        		   AlphaAnimation animation = new AlphaAnimation(1, 1f);
              	   animation.setFillAfter(true);              	   
              	   btn.setAnimation(animation);              	
              	   close = true;        		
        	   }
           } //end Onclick
       });  //**end setOnclicklistener
    }//**end oncreat  
   
    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Toast.makeText(this,"Clicked " + getListAdapter().getItem(position).toString(),Toast.LENGTH_SHORT).show();
        if(lammo==true){        	
        	mHandler.removeCallbacks(_run);
        	lammo = false;
        }         
    }
    private class CustomAdapter extends ArrayAdapter<HashMap<String, Object>>
	{ 
	   ViewHolder viewHolder;
	   public CustomAdapter(Context context, int textViewResourceId,
	   ArrayList<HashMap<String, Object>> players) {	 
	   super(context, textViewResourceId, players); 
	  } 
	   private class ViewHolder
	  {
	   ImageView photo;
	   TextView name,pro,sl;	
	  }
	
	 @Override
	 public View getView(final int position, View convertView, ViewGroup parent) {	 
	   if(convertView==null)
	    {
	   convertView=inflater.inflate(com.pj3.pos.R.layout.bep_list_item, null);
	   viewHolder=new ViewHolder();
	    viewHolder.photo=(ImageView) convertView.findViewById(com.pj3.pos.R.id.ivIcon);
	    viewHolder.name=(TextView) convertView.findViewById(com.pj3.pos.R.id.tvName);
	    viewHolder.pro=(TextView) convertView.findViewById(com.pj3.pos.R.id.tvPro);
	    viewHolder.sl = (TextView) convertView.findViewById(com.pj3.pos.R.id.tvSl);

	    convertView.setTag( viewHolder); }
	  else
	   viewHolder=(ViewHolder) convertView.getTag();
	   int photoId=(Integer) foods.get(position).get("photo");
	  viewHolder.photo.setImageDrawable(getResources().getDrawable(photoId));
	  viewHolder.name.setText(foods.get(position).get("name").toString());
	  viewHolder.pro.setText(foods.get(position).get("pro").toString());
	  
	   return convertView;
	  }
	 
	 }
}
