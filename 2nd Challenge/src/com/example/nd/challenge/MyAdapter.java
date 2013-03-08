package com.example.nd.challenge;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * This class is an ArrayAdapter and implements the ViewHolder pattern
 * @author Ehsan Barekati
 *
 */
public class MyAdapter extends ArrayAdapter<Equake>{
	
	private Equake[] quakes;
	private List<Equake> lquake;
	private Context context;
	private Equake quake;
	static final int WHITE = Color.argb(256, 0, 0, 0);
	static final int DARK_RED = Color.argb(150, 200, 0, 0);
	static final int LIGHT_RED = Color.argb(150, 150, 0, 0);

	/**
	 * The constructor that takes arrays
	 * @param context the context of the activity that uses this class
	 * @param textViewResourceId
	 * @param objects An array of Equakes that is used in creating the views
	 */
	public MyAdapter(Context context, int textViewResourceId, Equake[] objects) {
		super(context, textViewResourceId, objects);
		quakes = objects;
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The constructor that takes lists
	 * @param context the context of the activity that uses this class
	 * @param textViewResourceId
	 * @param objects An array of Equakes that is used in creating the views
	 */
	public MyAdapter(Context context, int textViewResourceId, List<Equake> objects) {
		super(context, textViewResourceId, objects);
		lquake = objects;
		this.context = context;
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		EquakesViewHolder viewHolder;
		
		quake = lquake.get(position);
		
		if (convertView == null) {
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflator.inflate(R.layout.customlayout, parent, false);
		
		viewHolder = new EquakesViewHolder();
		viewHolder.title = (TextView) convertView.findViewById(R.id.title);
		viewHolder.longi = (TextView) convertView.findViewById(R.id.longi);
		viewHolder.lat = (TextView) convertView.findViewById(R.id.lat);
		viewHolder.date = (TextView) convertView.findViewById(R.id.date);
		
		convertView.setTag(viewHolder);
		}
		else{
			viewHolder = (EquakesViewHolder) convertView.getTag();
		}
		
		viewHolder.title.setText(quake.getTitle());
		viewHolder.longi.setText(quake.getLong());
		viewHolder.lat.setText(quake.getLat());
		viewHolder.date.setText(quake.getDate());
		
		if (quake.getMagn() >= 7)
		{
			viewHolder.title.setBackgroundColor(DARK_RED);
			viewHolder.longi.setBackgroundColor(DARK_RED);
			viewHolder.lat.setBackgroundColor(DARK_RED);
			viewHolder.date.setBackgroundColor(DARK_RED);
		} else if (quake.getMagn() >= 5) {
			viewHolder.title.setBackgroundColor(LIGHT_RED);
			viewHolder.longi.setBackgroundColor(LIGHT_RED);
			viewHolder.lat.setBackgroundColor(LIGHT_RED);
			viewHolder.date.setBackgroundColor(LIGHT_RED);
		} else {
			viewHolder.title.setBackgroundColor(WHITE);
			viewHolder.longi.setBackgroundColor(WHITE);
			viewHolder.lat.setBackgroundColor(WHITE);
			viewHolder.date.setBackgroundColor(WHITE);
		}
		

		
		return convertView;

		
	}

/**
 * A private class the is used as a view holder
 * @author Ehsan Barekati
 *
 */
	private class EquakesViewHolder {
		TextView title;
		TextView longi;
		TextView lat;
		TextView date;
	}


}
