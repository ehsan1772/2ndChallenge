package com.example.nd.challenge;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.os.AsyncTask;

public class DataLoader extends AsyncTask<String, String, List<Equake>> {

	@Override
	protected void onPostExecute(List<Equake> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected List<Equake> doInBackground(String... urls) {
		// TODO Auto-generated method stub
		for (String urlstring : urls)
		{
	        /* Get a SAXParser from the SAXPArserFactory. */
	        SAXParserFactory spf = SAXParserFactory.newInstance();
	        SAXParser sp = null;
			try {
				sp = spf.newSAXParser();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        /* Get the XMLReader of the SAXParser we created. */
	        XMLReader xr = null;
			try {
				xr = sp.getXMLReader();
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        /* Create a new ContentHandler and apply it to the XML-Reader*/
	        XMLparser myExampleHandler = new XMLparser();
	        xr.setContentHandler(myExampleHandler);
	       
	        
	        URL url = null;
			try {
				url = new URL(urlstring);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        /* Parse the xml-data from our URL. */
	        try {
	        	InputSource source = new InputSource(url.openStream());
				xr.parse(source);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        /* Parsing has finished. */

	        /* Our ExampleHandler now provides the parsed data to us. */
	        List<Equake> parsedExampleDataSet = myExampleHandler.result();
	        
	        return parsedExampleDataSet;
		}
		
		return null;
	}

}
