package com.example.nd.challenge;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Equake[] testq;
	List<Equake> lquake;
	List<Equake> temp;
	ListView lv;
	ProgressBar pbar;
	DataLoader dloader;
	Myadapter myadapter;
	AlertDialog.Builder builder;
	DialogInterface.OnClickListener dialogClickListener;
	int deleteposition;
	TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button) findViewById(R.id.button1);
        pbar = (ProgressBar) findViewById(R.id.progressBar1);
        tv = (TextView) findViewById(R.id.textView1);
        tv.setVisibility(View.INVISIBLE);
        pbar.setVisibility(View.INVISIBLE);
        
        

        
        lv = (ListView) findViewById(R.id.listView1);
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	   public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
        	      Equake thequake = lquake.get(position);
        	    //  myadapter.remove(thequake);
        			Intent intent = new Intent(Intent.ACTION_VIEW);
        			intent.setData(Uri.parse(thequake.getLink()));
        			startActivity(intent);
        	   } 
        	
		});
        

       	   
        dialogClickListener = new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
		        switch (arg1){
		        case DialogInterface.BUTTON_POSITIVE:
		            //Yes button clicked
		      	      myadapter.remove(lquake.get(deleteposition));
		            break;

		        case DialogInterface.BUTTON_NEGATIVE:
		            //No button clicked
		            break;
		        }
			}

        };
        
 	   builder = new AlertDialog.Builder(this);
   	   builder.setMessage("Do you want to delete this item?").setPositiveButton("Yes", dialogClickListener)
   	      .setNegativeButton("No", dialogClickListener);
        
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
      	      deleteposition = arg2;
      	      builder.show();

				// TODO Auto-generated method stub
				return false;
			}


        	
		});
        
        

        if (isOnline())
        {
        dloader = new DataLoader();
        dloader.execute(new String[]{"http://earthquake.usgs.gov/earthquakes/shakemap/rss.xml"});
        tv.setVisibility(View.INVISIBLE);
        }
        else
        {
            tv.setVisibility(View.VISIBLE);
            if (myadapter != null)
            myadapter.clear();
        }
        
        OnClickListener l;
    
        l = new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        if (isOnline())
		        {
		        dloader = new DataLoader();
		        dloader.execute(new String[]{"http://earthquake.usgs.gov/earthquakes/shakemap/rss.xml"});
		        tv.setVisibility(View.INVISIBLE);
		        }
		        else
		        {
		            tv.setVisibility(View.VISIBLE);
		            if (myadapter != null)
		            myadapter.clear();
		        }
		        
				
			}
		};
		button.setOnClickListener(l);
    }


    
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
    
    private class DataLoader extends AsyncTask<String, String, List<Equake>> {

    	@Override
    	protected void onPostExecute(List<Equake> result) {
    		// TODO Auto-generated method stub


			if (temp != null)
    		{
        		List<Equake> temp = lquake;
        		Log.d("temp size : ", String.valueOf(temp.size()));
        		Log.d("result size : ", String.valueOf(result.size()));
    			Equake lastquake = result.get(result.size() - 1);
    			while (!temp.get(temp.size() - 1).getTitle().equals(lastquake.getTitle()))
    			{
    				Log.d("temp", temp.get(temp.size() - 1).getTitle());
    				Log.d("last quake", lastquake.getTitle());
    				result.add(temp.get(temp.size() - 1));
    				temp.remove(temp.size() -1);
    			}
    		}
    		lquake = result;
    		temp = lquake;
            myadapter = new Myadapter(getBaseContext(), R.layout.customlayout, result);
            lv.setAdapter(myadapter);
           
            pbar.setVisibility(View.INVISIBLE);
    		super.onPostExecute(result);
    	}

    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
            pbar.setVisibility(View.VISIBLE);
    		super.onPreExecute();
    	}

    	@Override
    	protected List<Equake> doInBackground(String... urls) {
    		// TODO Auto-generated method stub
    		List<Equake> parsedExampleDataSet = null;

    			tv.setVisibility(View.INVISIBLE);
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
    	        parsedExampleDataSet = myExampleHandler.result();
    	        
    	//        return myExampleHandler.result();
    		}
    		
    		
    		
    	//	return null;

    		
    		return parsedExampleDataSet;
    	}
    	


    }
}
