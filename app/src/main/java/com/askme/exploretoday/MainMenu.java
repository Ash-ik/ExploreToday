package com.askme.exploretoday;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity {
	Button browseNewspaper;
	String str;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        browseNewspaper=(Button) findViewById(R.id.browseNewspaper);
        browseNewspaper.setOnClickListener(new OnClickListener() {
 		
 		@Override
 		public void onClick(View v) {
 			// TODO Auto-generated method stub
 			PopupMenu popup=new PopupMenu(MainMenu.this,v);
 			popup.getMenuInflater().inflate(R.menu.pop_up_menu,popup.getMenu());
 			popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
 				
 				@Override
 				public boolean onMenuItemClick(MenuItem item) {
 					// TODO Auto-generated method stub
 					str=item.toString();
 					if(str.equals("Bangla Newspapers"))
 					{
 						Intent clicked=new Intent(MainMenu.this,Bangla_details.class);
 						startActivity(clicked);
 					}
 					if(str.equals("English Newspapers"))
 					{
 						Intent clicked=new Intent(MainMenu.this,English_details.class);
 						startActivity(clicked);
 					}
 					return true;
 				}
 			});
 			popup.show();
 			
 		}
 	});
	
       menu_method();
        
    }
	
    
    private void menu_method(){
		Button Exit=(Button) findViewById(R.id.button_exit);
		Button aboutUs=(Button) findViewById(R.id.button_aboutUs);
		Button help=(Button) findViewById(R.id.button_help);
		
		
		Exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog dialog=new AlertDialog(MainMenu.this) {};
				
				dialog.setButton("Yes",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						System.exit(0);
					}
				});
				dialog.setButton2("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
				dialog.setMessage("Are you sure You want to exit?");
			dialog.show();
			
			}
		});
	
    aboutUs.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(MainMenu.this,AboutUs.class));
		}
	});
    
    help.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(MainMenu.this,Help.class));
		}
	});
    
    
    
    }
}