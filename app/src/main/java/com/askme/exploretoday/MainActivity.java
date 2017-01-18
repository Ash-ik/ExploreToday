package com.askme.exploretoday;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.os.Build;

public class MainActivity extends Activity implements AnimationListener{

	Animation animFadein;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_view);
        
        ImageView image=(ImageView) findViewById(R.id.imageView);
        
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.animator.fade_in); 
        
       
        
        animFadein.setAnimationListener(this);
        image.startAnimation(animFadein);
    }

	@Override
	public void onAnimationEnd(Animation arg0) {
		startActivity(new Intent(MainActivity.this,MainMenu.class));
	finish();	
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
}
