package com.example.nd.challenge;

import java.util.List;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements EarthquakeEliminator, MyListViewOwner, MyXMLDataLoaderOwner, OnClickListener{

	private Equake[] testq;
	private List<Equake> lquake;
	private List<Equake> temp;
	private ListView lv;
	private MyListView myListView;
	private ProgressBar pbar;
	private DataLoader dloader;
	private Myadapter myadapter;
	private AlertDialog.Builder builder;
	private DialogInterface.OnClickListener dialogClickListener;
	private int deleteposition;
	private TextView tv;
	private Button refreshButton;
	
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

	public Equake getQuake(int position){
	    Equake theQuake = lquake.get(position);
	    return theQuake;
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
    


	public void eliminateEarthquake(int number) {
		// TODO Auto-generated method stub
		myadapter.remove(lquake.get(number));
	}

	public Object getClickedItem(int position) {
	    Equake theQuake = lquake.get(position);
	    return theQuake;
	}

	public void deleteClickedItem(int position) {
		// TODO Auto-generated method stub
		Log.d("Position", String.valueOf(position));
		myadapter.remove(lquake.get(position));
	}

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
        myadapter = new Myadapter(getBaseContext(), R.layout.customlayout, result);
        myListView.setAdapter(myadapter);
        pbar.setVisibility(View.INVISIBLE);
		
	}

	public void onPreXMLLoaderExecute() {
		pbar.setVisibility(View.VISIBLE);
		
	}
	private void loadData(){
        if (isOnline())
        {
        tv.setVisibility(View.INVISIBLE);
        MyXMLDataLoader myXMLDateLoader = new MyXMLDataLoader(this);
        myXMLDateLoader.execute(new String[]{"http://earthquake.usgs.gov/earthquakes/shakemap/rss.xml"});
        }
        else
        {
            tv.setVisibility(View.VISIBLE);
            if (myadapter != null)
            myadapter.clear();
        }
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		loadData();
	}
}
