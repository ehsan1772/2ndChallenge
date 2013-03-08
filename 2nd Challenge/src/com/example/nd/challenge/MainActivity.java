package com.example.nd.challenge;

import java.io.FileNotFoundException;
import java.util.List;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * This is the main activity that is used in this app
 * 
 * @author Ehsan Barekati
 *
 */
public class MainActivity extends Activity implements MyListViewOwner, MyXMLDataLoaderOwner, OnClickListener{

	private List<Equake> lquake;
	private List<Equake> temp;
	private MyListView myListView;
	private ProgressBar pbar;
	private MyAdapter myadapter;
	private TextView tv;
	private Button refreshButton;
	
	/**
	 * The onCreate method registers all the views and set the necessary parameters on them.
	 * It also invokes the loadData() method to load the earthquakes.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        setContentView(R.layout.activity_main);
        
        refreshButton = (Button) findViewById(R.id.button1);
        pbar = (ProgressBar) findViewById(R.id.progressBar1);
        tv = (TextView) findViewById(R.id.textView1);
        myListView = (MyListView) findViewById(R.id.listView1);
        
        tv.setVisibility(View.INVISIBLE);
        pbar.setVisibility(View.INVISIBLE);
        
        myListView.setTheOwner(this);     
        refreshButton.setOnClickListener(this);

        loadData();
        
    }


    
    /**
     * This method is part of the MyListViewOwner interface
     * @return The object related to the view that was clicked
     */
	public Object getClickedItem(int position) {
	    Equake theQuake = lquake.get(position);
	    return theQuake;
	}

	/**
	 * This method is part of the MyListViewOwner interface 
	 * and gets invoked by the ListView to delete the clicked item
	 */
	public void deleteClickedItem(int position) {
		// TODO Auto-generated method stub
		Log.d("Position", String.valueOf(position));
		myadapter.remove(lquake.get(position));
	}

	/**
	 * This method gets invoked by the MyXMLLoader class to return the results 
	 * and is part of the MyXMLLoaderOwner interface
	 */
	public void onPostXMLLoaderExecute(Object objectResult) {
		
		List<Equake> result = (List<Equake>) objectResult;
		
		if (temp != null)
		{
    		List<Equake> temp = lquake;
			Equake lastquake = result.get(result.size() - 1);
			while (!temp.get(temp.size() - 1).getTitle().equals(lastquake.getTitle()))
			{
				result.add(temp.get(temp.size() - 1));
				temp.remove(temp.size() -1);
			}
		}
		lquake = result;
		temp = lquake;
        myadapter = new MyAdapter(getBaseContext(), R.layout.customlayout, result);
        myListView.setAdapter(myadapter);
        pbar.setVisibility(View.INVISIBLE);
        refreshButton.setText("Refresh");
		
	}

	/**
	 * 
	 */
	public void onPreXMLLoaderExecute() {
		pbar.setVisibility(View.VISIBLE);
        refreshButton.setText("");
	}
	
	/**
	 * This method gets invoked at the end of the onCreate method to load the XML file
	 */
	private void loadData(){
		MyDownloadManager myDownloadManager = new MyDownloadManager(this, this);
        if (MyNetworkManager.isOnline(this))
        {
        tv.setVisibility(View.INVISIBLE);
        myDownloadManager.download("http://earthquake.usgs.gov/earthquakes/shakemap/rss.xml");

        }
        else
        {
        	try {
				myDownloadManager.loadSavedInputStream();
			} catch (FileNotFoundException e) {
	            tv.setVisibility(View.VISIBLE);
	            if (myadapter != null)
	            myadapter.clear();
			}

        }
	}

	/**
	 * 
	 */
	public void onClick(View v) {
		loadData();
	}
}
