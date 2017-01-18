package com.askme.exploretoday;

import java.util.Locale;

import android.app.Activity;
import android.app.Application;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class myWebview extends Activity {
	String Title,Description;
	TextView newsTitle,newsDescription;
	Button back;
	Button Speak;
	TextToSpeech tts;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.my_webview);
            
            
            Typeface font=Typeface.createFromAsset(getAssets(),"fonts/Siyamrupali.ttf");
            newsTitle=(TextView) findViewById(R.id.news_caption);
            newsTitle.setTypeface(font);
            newsDescription=(TextView)findViewById(R.id.news_details);
            back=(Button) findViewById(R.id.news_back);
            Speak=(Button)findViewById(R.id.buttonSpeak);
            //            String URL=getIntent().getExtras().getString("URL");
            Title=getIntent().getExtras().getString("Title");
            Description=getIntent().getExtras().getString("Description");
	
            newsTitle.setText(Title);
            newsDescription.setText(Description);
            
            
            
            tts=new TextToSpeech(myWebview.this,new TextToSpeech.OnInitListener() {
				
				@Override
				public void onInit(int status) {
					// TODO Auto-generated method stub
				if(status!=TextToSpeech.ERROR)
				{
					tts.setLanguage(Locale.US);
				}
				}
				
			});
            
            
            
            back.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
            
            Speak.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					tts.speak(Description, TextToSpeech.QUEUE_FLUSH,null);
				}
			});
            
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
