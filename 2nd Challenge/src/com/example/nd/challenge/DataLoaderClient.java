package com.example.nd.challenge;

/**
 * This interface should be implemented by any class that wants to use the DataLoader class
 * The methods will be used as the callbacks by the DataLoader class
 * 
 * @author Ehsan Barekati
 *
 */
public interface DataLoaderClient {
	/**
	 * Should be used to make the necessary changes on the UI, for example initiating a progress bar.
	 */
	void OnDataLoaderPreExecute();
	/**
	 * Should be used to receive the results.
	 * 
	 * @param result The results will be returned as an Object instance. It should be casted to the appropriate type if necessary.
	 */
	void OnDataLoaderPostExecute(Object result);
}
