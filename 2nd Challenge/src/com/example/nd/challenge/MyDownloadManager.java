package com.example.nd.challenge;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

/**
 * This class uses the DownloadManager instance to download a file from a URL and registers a broadcast receiver to acquire the downloaded file
 * @author Ehsan Barekati
 *
 */
@TargetApi(9)
public class MyDownloadManager {
	
	private DownloadManager downloadManager; 
	private long myDownloadReference;
	private IntentFilter filter;
	private Context context;
	private InputStream result;
	private SharedPreferences mySharedPreferences;
	private MyXMLDataLoaderOwner theOwner;
	
	/**
	 * The constructor
	 * @param context the activity's context
	 * @param theOwner the activity that has a MyXMLDataLoader
	 */
	MyDownloadManager(Context context, MyXMLDataLoaderOwner theOwner){
		filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE); 
		String serviceString = Context.DOWNLOAD_SERVICE; 
		downloadManager = (DownloadManager) context.getSystemService(serviceString);
		mySharedPreferences = context.getSharedPreferences("MY_PREFS", Activity.MODE_PRIVATE);
		this.theOwner = theOwner;
		this.context = context;
	}

	/**
	 * sends the URL to the downloadmanager's queue and registers the respected broadcast receiver 
	 * @param myURL The URL that should be called
	 */
	public void download(String myURL){
		Uri uri = Uri.parse(myURL); 
		DownloadManager.Request request = new Request(uri); 
		myDownloadReference = downloadManager.enqueue(request);	
		
		BroadcastReceiver receiver = new MyReceiver();
		context.registerReceiver(receiver, filter);
	}

	/**
	 * Opens the file upon the completion of download, or a persisted file from the external storage
	 * @param reference The reference of the downloaded file on the external storage
	 */
	private void fileReader(long reference){
		try {
			
			ParcelFileDescriptor file = downloadManager.openDownloadedFile(reference);
			result = new FileInputStream(file.getFileDescriptor());
			MyXMLDataLoader myDataLoader = new MyXMLDataLoader(theOwner);
			myDataLoader.execute(result);
            
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * It saves a reference to the lates version of the downloaded file through shared preferences
	 * @param reference the reference to the file
	 */
	private void saveFileReference(long reference){
		Editor editor = mySharedPreferences.edit();
		editor.putLong("INPUT_REFERENCE", reference);
		editor.apply();
	}
	
	/**
	 * Gets the reference to the latest persisted file and invokes the filereader method
	 * @throws FileNotFoundException throws an exception if the file doesn't exist
	 */
	public void loadSavedInputStream() throws FileNotFoundException {
		long reference = mySharedPreferences.getLong("INPUT_REFERENCE", -1);
		if (reference == -1)
			throw new FileNotFoundException();
		else
			fileReader(reference);
	}
	
	/**
	 * A broadcast receiver that recieves the downloaded file upon the download completion
	 * @author Ehsan Barekati
	 *
	 */
	private class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			
			long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1); 
			if (myDownloadReference == reference) {
				saveFileReference(reference);
				fileReader(reference);
			}
				
			
		}
		
	}
}


