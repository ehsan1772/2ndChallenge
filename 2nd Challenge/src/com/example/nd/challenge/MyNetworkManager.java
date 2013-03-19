package com.example.nd.challenge;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class includes static methods that can be used to check and alter the network status
 * @author Ehsan Barekati
 *
 */
public class MyNetworkManager {

	/**
	 * This method determines if the network connectivity is available    
	 * @return true if the network is available
	 */
	public static boolean isOnline(Context context) {
	    ConnectivityManager cm =
	        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
}
