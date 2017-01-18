package com.askme.exploretoday;

import java.util.Locale;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Help extends Activity {
	
	private Integer[] iconList={R.drawable.image1,R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5};
	
	private String[]detailsInfo={"You can choose Bangla or English Newspapers to see todays Bangla or English news","You can choose Bangla or English Newspapers to see todays Bangla or English news","You can go to prothom alo or Shomokal from here to read out todays Bangla news","You can go to BD News 24 or The Bangladesh Today from here to read out todays English news","If you select one of the bangla or english newspaper, it will create a list containing all the headlines. Click on a news to open it" , "Your desired news will be opened in this way . Scroll anywhere to read the news. Also there is a speaker button on the upper middle of the screen. If you click that button, it will read out that news"};
		private int i=1;
	ImageView iv;
	TextView tv;
	TextToSpeech tts;
	Button home;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        finish();
        tts = new TextToSpeech(Help.this,
				new TextToSpeech.OnInitListener() {

					@Override
					public void onInit(int status) {
						// TODO Auto-generated method stub
						if (status != TextToSpeech.ERROR) {
							tts.setLanguage(Locale.US);
						}
					}
				});
        
        iv=(ImageView) findViewById(R.id.imageView);
        home=(Button) findViewById(R.id.helpExit);
        
        Button next=(Button) findViewById(R.id.button1);
        Button previous=(Button) findViewById(R.id.button2);
        tv=(TextView) findViewById(R.id.textDetails);
        tv.setText(detailsInfo[i]);
        tts.speak(detailsInfo[i],TextToSpeech.QUEUE_FLUSH,null);
        
        next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(i==5)
					i=1;
				else i++;
				iv.setImageResource(iconList[i]);
				tv.setText(detailsInfo[i]);
				tts.speak(detailsInfo[i],TextToSpeech.QUEUE_FLUSH,null);
			}
		});
        previous.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				if(i==1)
					finish();
				else i--;
				iv.setImageResource(iconList[i]);
				tv.setText(detailsInfo[i]);
				tts.speak(detailsInfo[i],TextToSpeech.QUEUE_FLUSH,null);
			}
		});
        
        
        home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
	}
}
