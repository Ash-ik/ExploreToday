package com.askme.exploretoday;

import java.util.Locale;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class newsPaper_launcher extends Activity {
	WebView web;
	ProgressDialog loading;
	Button speak;
	Button home;
	TextToSpeech tts;
	String temp;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_newspaper);
        speak=(Button) findViewById(R.id.buttonSpeak);
        home=(Button) findViewById(R.id.buttonHome);
		tts = new TextToSpeech(newsPaper_launcher.this,
				new TextToSpeech.OnInitListener() {

					@Override
					public void onInit(int status) {
						// TODO Auto-generated method stub
						if (status != TextToSpeech.ERROR) {
							tts.setLanguage(Locale.US);
						}
					}
				});
		
        web=(WebView) findViewById(R.id.webView);
        web.setWebViewClient(new myWebClient());
        web.getSettings().setJavaScriptEnabled(true);
        //paste webview offline property
        
        String URL=getIntent().getExtras().getString("URL");
        web.loadUrl(URL);
        
        
        loading=new ProgressDialog(newsPaper_launcher.this);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.setMessage("Please wait while we load\n"+URL);
        loading.setIndeterminate(true);
        loading.setCancelable(true);
        loading.show();
        
       
		speak.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String temp=getIntent().getExtras().getString("Description");
				tts.speak(temp, TextToSpeech.QUEUE_FLUSH, null);
			}
		});
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder dialog=new AlertDialog.Builder(newsPaper_launcher.this);
				dialog.setMessage("Are you sure?");
				dialog.setCancelable(false);
				dialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						finish();
					}
				});
				
				dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						
					}
				}); 
				AlertDialog alert=dialog.create();
				alert.show();
				
			}
		});
		
}
	
	
	
	
	
	
	
	
	
	
	public class myWebClient extends WebViewClient
	{

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
			loading.cancel();
		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK && web.canGoBack())
		{
			web.goBack();
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	protected void onPause() {
		if(tts!=null)
		{
			tts.stop();
			tts.shutdown();
		}
		// TODO Auto-generated method stub
		super.onPause();
	}
}
