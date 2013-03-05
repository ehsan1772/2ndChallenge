package com.example.nd.challenge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomView extends LinearLayout {
	
	Context context;
	
	TextView title;
	TextView longitude;
	TextView latitude;
	TextView time;

	public CustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	private void initialize(Context context)
	{
		this.context = context;
		
		LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0);
		param.gravity = Gravity.CENTER_HORIZONTAL;
		
		title = new TextView(context);
		longitude = new TextView(context);
		latitude = new TextView(context);
		time = new TextView(context);
		
		title.setText("Title");
		longitude.setText("Longitude");
		latitude.setText("Latitude");
		time.setText("Time");
		
		title.setLayoutParams(param);
		longitude.setLayoutParams(param);
		latitude.setLayoutParams(param);
		time.setLayoutParams(param);
		
		addView(title);
		addView(longitude);
		addView(latitude);
		addView(time);
		
		
	}
	
	public void setTitle(String thetitle)
	{
		title.setText(thetitle);
	}
	
	public void setLongitude(String thelongitude)
	{
		longitude.setText(thelongitude);
	}
	
	public void setLatitude(String thelatitude)
	{
		latitude.setText(thelatitude);
	}
	
	public void setTime(String thetime)
	{
		title.setText(thetime);
	}

}
