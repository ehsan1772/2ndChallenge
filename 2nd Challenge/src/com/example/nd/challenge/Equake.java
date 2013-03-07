package com.example.nd.challenge;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * This class represents an earthquake that is extracted from this link:
 * http://earthquake.usgs.gov/earthquakes/shakemap/rss.xml
 * 
 * @author Ehsan Barekati
 *
 */
public class Equake implements Serializable {
	

	private static final long serialVersionUID = 3827078672841030929L;
	private String title;
	private String lat;
	private String longi;
	private Date date;
	private String link;
	private int magn;
	
	public SimpleDateFormat simpleDateFormatset;
	public SimpleDateFormat simpleDateFormatget;
	
	/**
	 * The default constructor to create the earthquake
	 */
	public Equake()	{
		date = new Date();
		
        simpleDateFormatset = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
        simpleDateFormatset.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        simpleDateFormatget = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
        simpleDateFormatget.setTimeZone(TimeZone.getDefault());
	}
	
	public void setDate(String description)	{ 

    	int start = description.indexOf("Date: ") + 6;
    	int end = start + 25;
    	char[] datearray = new char[25];
    	description.getChars(start, end, datearray, 0);

		String datestring = new String(datearray);
		
        try {
			date = simpleDateFormatset.parse(datestring);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getDate()	{
		return simpleDateFormatget.format(date);
	}
	
	public int getMagn() {
		return magn;
	}
	
	public void setMagn(String magn) {
		this.magn = Integer.parseInt(magn);
	}
	
	public String getLink()	{
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getLong()	{
		return longi;
	}
	
	public void setLong(String longitute) {
		this.longi = longitute;
	}
	
	public String getLat() {
		return lat;
	}
	
	public void setLat(String latitude)	{
		this.lat = latitude;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	

}
