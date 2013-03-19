package com.example.nd.challenge;

/**
 * This interface should be implemented by any class that uses 
 * the MyAlterDialogue and includes the onClick callbacks to the buttons
 * @author Ehsan Barekati
 *
 */
public interface MyAlterDialogueOwner {
	/**
	 * Gets invoked upon clicking the Yes button
	 * @param position The position of the view that is attached to the dialogue
	 */
	void onYesClicked(int position);
	/**
	 * Gets invoked upon clicking the No button
	 * @param position The position of the view that is attached to the dialogue
	 */
	void onNoClicked(int position);
}
