package com.example.nd.challenge;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Myadapter extends ArrayAdapter<Equake>{
	
	Equake[] quakes;
	List<Equake> lquake;
	Context context;
	Equake quake;

	public Myadapter(Context context, int textViewResourceId, Equake[] objects) {
		super(context, textViewResourceId, objects);
		quakes = objects;
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		Equake quake = quakes[position];
		quake = lquake.get(position);
		
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowview = inflator.inflate(R.layout.customlayout, parent, false);
		TextView title = (TextView) rowview.findViewById(R.id.title);
		TextView longi = (TextView) rowview.findViewById(R.id.longi);
		TextView lat = (TextView) rowview.findViewById(R.id.lat);
		TextView date = (TextView) rowview.findViewById(R.id.date);
		
		title.setText(quake.getTitle());
		longi.setText(quake.getLong());
		lat.setText(quake.getLat());
		date.setText(quake.getDate());
		
		if (quake.getMagn() >= 7)
		{
			title.setBackgroundResource(android.R.color.holo_red_dark);
			longi.setBackgroundResource(android.R.color.holo_red_dark);
			lat.setBackgroundResource(android.R.color.holo_red_dark);
			date.setBackgroundResource(android.R.color.holo_red_dark);
		}
		else if (quake.getMagn() >= 5)
		{
			title.setBackgroundResource(android.R.color.holo_red_light);
			longi.setBackgroundResource(android.R.color.holo_red_light);
			lat.setBackgroundResource(android.R.color.holo_red_light);
			date.setBackgroundResource(android.R.color.holo_red_light);
		}
		

		
		return rowview;

		
	}

	public Myadapter(Context context, int textViewResourceId,
			List<Equake> objects) {
		super(context, textViewResourceId, objects);
		lquake = objects;
		this.context = context;
		// TODO Auto-generated constructor stub
	}





}
