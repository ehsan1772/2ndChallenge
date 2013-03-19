package com.example.nd.challenge;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.os.AsyncTask;
import android.util.Log;

/**
 * This class uses the SAXParserary to parse the XML file in a background thread
 * @author Ehsan Barekati
 *
 */
public class MyXMLDataLoader extends AsyncTask<InputStream, Void, Object> {
	
	private MyXMLDataLoaderOwner theOwner;
	private SAXParserFactory mySaxParserFactory;
	private SAXParser mySAXParser;
	private XMLReader myXMLReader;
	private XMLparser myExampleHandler;
	private InputSource source;
	private List<Equake> parsedExampleDataSet;
	private InputStream myStream;
	
	MyXMLDataLoader(MyXMLDataLoaderOwner theOwner){
		this.theOwner = theOwner;
	}



	@Override
	protected void onPreExecute() {
		theOwner.onPreXMLLoaderExecute();
		super.onPreExecute();
	}



	@Override
	protected Object doInBackground(InputStream... params) {

		myStream = params[0];

		try {
        /* Get a SAXParser from the SAXPArserFactory. */
		mySaxParserFactory = SAXParserFactory.newInstance();
		mySAXParser = mySaxParserFactory.newSAXParser();

        /* Get the XMLReader of the SAXParser we created. */
        myXMLReader = mySAXParser.getXMLReader();

        /* Create a new ContentHandler and apply it to the XML-Reader*/
        myExampleHandler = new XMLparser();
        myXMLReader.setContentHandler(myExampleHandler);

        /* Parse the xml-data from the InputStream */
        source = new InputSource(myStream);
        myXMLReader.parse(source);


        /* Parsing has finished. */

        /* Our ExampleHandler now provides the parsed data to us. */
        parsedExampleDataSet = myExampleHandler.result();
        
		} catch (Exception e) {
			exceptionManager(e);
		}
	
		return parsedExampleDataSet;
	}
	
	private void exceptionManager(Exception e){
		if (e instanceof SAXException)
		{
			errprMessageLogger("SAXException", e);
			return;
		}
		if (e instanceof IOException)
		{
			errprMessageLogger("IOException", e);
			return;
		}
		if (e instanceof MalformedURLException)
		{
			errprMessageLogger("MalformedURLException", e);
			return;
		}
		if (e instanceof ParserConfigurationException)
		{
			errprMessageLogger("ParserConfigurationException", e);
			return;
		}	
		errprMessageLogger("Unknowen Exception", e);
	}
	
	private void errprMessageLogger(String msg, Exception e){
		Log.e("Exception in MyXMLDataLoader", msg);
		e.printStackTrace();
	}
	
	@Override
	protected void onPostExecute(Object result) {
		theOwner.onPostXMLLoaderExecute(result);
		super.onPostExecute(result);
	}

	}


