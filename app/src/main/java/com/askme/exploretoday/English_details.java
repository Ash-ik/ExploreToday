package com.askme.exploretoday;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class English_details extends ListActivity implements OnClickListener{
	
	String name="";
	Intent click;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.newspaper);
	        
	        
	        Button back=(Button) findViewById(R.id.back_button_1);
		    back.setOnClickListener(this);

	        
	        setListAdapter(new MyAdapter(this, 
	        		android.R.layout.simple_list_item_1 ,R.id.paper_name ,
	        		getResources().getStringArray(R.array.newspaper_list_english)));
	 }




	public class MyAdapter extends ArrayAdapter<String> {

			public MyAdapter(Context context, int resource, int textViewResourceId,
					String[] strings) {
				super(context, resource, textViewResourceId, strings);
				// TODO Auto-generated constructor stub
			}
			
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View row=inflater.inflate(R.layout.list, parent, false);
				String[] items=getResources().getStringArray(R.array.newspaper_list_english) ;
				
				ImageView iv =(ImageView) row.findViewById(R.id.icon);
				
				TextView tv = (TextView) row.findViewById(R.id.paper_name);
				
				tv.setText(items[position]);
				
				if(items[position].equals("BD News 24")){
					iv.setImageResource(R.drawable.bdnews24_icon);
				}
				
				
				else if(items[position].equals("THE Bangladesh Today")){
					
					iv.setImageResource(R.drawable.the_bangladesh_today);
				}
				
				
				return row;
			}
			
		}
	
	
	 protected void onListItemClick(ListView l, View v, int position, long id) {
			super.onListItemClick(l, v, position, id);
			
			String[] items=getResources().getStringArray(R.array.newspaper_list_english) ;
			
			if(items[position].equals("BD News 24")){
				
						
						name="http://bdnews24.com/?widgetName=rssfeed&widgetId=1150&getXmlFeed=true";
							click=new Intent(English_details.this,list_activity.class);
							click.putExtra("newspaper_name",name);
							startActivity(click);
							name="";
							
			
			}
			
			else if(items[position].equals("THE Bangladesh Today")){
						
							name="http://thebangladeshtoday.com/feed/";
							click=new Intent(English_details.this,list_activity.class);
							click.putExtra("newspaper_name",name);
							startActivity(click);
							name="";	
						
			}
		}
		

	
	
	

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.back_button_1){
			finish();
		}
	}

}
