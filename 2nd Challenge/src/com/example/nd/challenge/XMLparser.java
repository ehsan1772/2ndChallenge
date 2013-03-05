package com.example.nd.challenge;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class XMLparser extends DefaultHandler{
	
	int elementstatus = 0;
	StringBuffer content;
	List<Equake> quakes;
	Equake quake;
	// elementstatus map:
	// item  = 1
	// title = 2
	// description = 3
	// link = 4
	// pubDate = 5
	// geo:lat = 6
	// geo:long = 7
	// dc:subject = 8
	// eq:seconds = 9
	// eq:depth = 10
	// eq:region = 11
	// channel = 12
	
	XMLparser()
	{
		quakes = new ArrayList<Equake>();
	}
	
	public List<Equake> result()
	{
		return quakes;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		content.append(ch, start, length);
//		super.characters(ch, start, length);
	}


	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		
		String charachter = content.toString();

		
		switch(elementstatus)
		{
		case 1:
			
			break;
		case 2:
			quake = new Equake();
			Log.d("Title is : ", charachter);
			quake.setTitle(charachter);
			break;
		case 3:
			Log.d("description is : ",charachter);
			quake.setDate(charachter);
			break;
		case 4:
			quake.setLink(charachter);
			break;
		case 5:
			
			break;
		case 6:
			quake.setLat(charachter);
			break;
		case 7:
			quake.setLong(charachter);
			break;
		case 8:
			quake.setMagn(charachter);
			quakes.add(quake);
			break;
		case 9:
			
			break;
		case 10:
			
			break;
		case 11:
			
			break;
		case 12:
			
			break;
		
			
		}
		super.endElement(uri, localName, qName);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		
		content = new StringBuffer();
		
		if (qName == "channel")
			elementstatus = 12;
		
		if (qName == "title" && elementstatus != 12)
			elementstatus = 2;
		
		if (qName == "description" && elementstatus != 12)
			elementstatus = 3;
		
		if (qName == "link" && elementstatus != 12)
			elementstatus = 4;
		
		if (qName == "pubDate")
			elementstatus = 5;
		
		if (qName == "item")
			elementstatus = 1;
		
		if (qName == "geo:lat")
			elementstatus = 6;
		
		if (qName == "geo:long")
			elementstatus = 7;
		
		if (qName == "dc:subject")
			elementstatus = 8;
		
		if (qName == "eq:seconds")
			elementstatus = 9;
		
		if (qName == "eq:depth")
			elementstatus = 10;
		
		if (qName == "eq:region")
			elementstatus = 11;
//		super.startElement(uri, localName, qName, attributes);
	}

}
