package com.example.nd.challenge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * This class extends AlertDialog.Builder and sets up onClick listener call back for the buttons
 * @author Ehsan Barekati
 *
 */
public class MyAlterDialogue extends AlertDialog.Builder implements DialogInterface.OnClickListener {

	private MyAlterDialogueOwner theOwner;
	private int position;
	
	/**
	 * The constructor, it sets up the texts and callbacks
	 * @param arg0 The context of the activity that this dialogue will belong with
	 * @param theOwner The class that implements MyAlterDialogueOwner interface
	 */
	public MyAlterDialogue(Context arg0, MyAlterDialogueOwner theOwner) {
		super(arg0);
	   	this.setMessage("Do you want to delete this item?");
	   	this.setPositiveButton("Yes", this);
	   	this.setNegativeButton("No", this);
	   	this.theOwner = theOwner;
	}
	
	/**
	 * Sets the position of the view that was invoked in MyAlterDialogueOwner
	 * @param position
	 */
	public void setPosition(int position){
		this.position = position;
	}
	
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
        switch (which){
        case DialogInterface.BUTTON_POSITIVE:
            //Yes button clicked
      	      theOwner.onYesClicked(position);
            break;

        case DialogInterface.BUTTON_NEGATIVE:
            //No button clicked
        	theOwner.onNoClicked(position);
            break;
        }
	}

}
