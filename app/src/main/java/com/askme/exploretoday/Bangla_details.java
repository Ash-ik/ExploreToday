package com.askme.exploretoday;

import android.annotation.SuppressLint;
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

public class Bangla_details extends ListActivity implements OnClickListener{
	String name="";
	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.newspaper);
	        
	        
	        Button back=(Button) findViewById(R.id.back_button_1);
		    back.setOnClickListener(this);

	        
	        setListAdapter(new MyAdapter(this, 
	        		android.R.layout.simple_list_item_1 ,R.id.paper_name ,
	        		getResources().getStringArray(R.array.newspaper_list_bangla)));
	        
	        
	        
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
				String[] items=getResources().getStringArray(R.array.newspaper_list_bangla) ;
				
				ImageView iv =(ImageView) row.findViewById(R.id.icon);
				
				TextView tv = (TextView) row.findViewById(R.id.paper_name);
				
				tv.setText(items[position]);
				
				if(items[position].equals("Prothom-Alo")){
					iv.setImageResource(R.drawable.prothom_alo);
				//	tv.setOnClickListener((OnClickListener) this);
				}
				
				
				else if(items[position].equals("Somokal")){ 
					
					iv.setImageResource(R.drawable.somokal);
				//	tv.setOnClickListener((OnClickListener) this);
				}
				else if(items[position].equals("Kaler Kantha")){

					iv.setImageResource(R.drawable.kalker_kantha);
					//	tv.setOnClickListener((OnClickListener) this);
				}


				//add image icon reference here

				return row;
			}
			
		}
	
	
	 
	 protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String[] items=getResources().getStringArray(R.array.newspaper_list_bangla) ;
		
		if(items[position].equals("Prothom-Alo")){
			
					Intent click;
					name="http://www.prothom-alo.com/";
						click=new Intent(Bangla_details.this,newsPaper_launcher.class);
						click.putExtra("URL",name);
						click.putExtra("Description","This feature is currently unavailable for this site");
						startActivity(click);
						name="";

		
		}
		
		else if(items[position].equals("Somokal")){
			
			
					Intent click;
						name="http://www.samakal.net/";
						click=new Intent(Bangla_details.this,newsPaper_launcher.class);
						click.putExtra("URL",name);
						click.putExtra("Description","This feature is currently unavailable for this site");
						startActivity(click);
						name="";		
		}
		else if(items[position].equals("Kaler Kantha")){


			Intent click;
			name="http://www.kalerkantho.com/";
			click=new Intent(Bangla_details.this,newsPaper_launcher.class);
			click.putExtra("URL",name);
			click.putExtra("Description","This feature is currently unavailable for this site");
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
