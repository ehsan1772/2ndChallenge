package com.example.nd.challenge;

import java.io.Serializable;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

/**
 * This class extends ListView and registered appropriate callbacks through implementing OnItemLongClickListener and MyAlterDialogueOwner interfaces
 * It gets added to the activity in the XML layout
 * @author Ehsan Barekati
 *
 */
public class MyListView extends ListView implements OnItemClickListener, OnItemLongClickListener, MyAlterDialogueOwner, Serializable {

	private MyListViewOwner theOwner; 
	private Context context;
	private MyAlterDialogue alterDialogue;
	
	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize(context);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(context);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context) {
		super(context);
		initialize(context);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * gets calls in constructors and registers the onClickListeners
	 * @param context
	 */
	private void initialize(Context context){
		this.context = context;
		this.setOnItemClickListener(this);
		this.setOnItemLongClickListener(this);
		alterDialogue = new MyAlterDialogue(context, this);
	}
	 
	 public void setTheOwner(MyListViewOwner theOwner){
		 this.theOwner = theOwner;
	 }

	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		alterDialogue.setPosition(arg2);
		alterDialogue.show();
		return false;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	      Equake thequake = (Equake) theOwner.getClickedItem(arg2);
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(thequake.getLink()));
			context.startActivity(intent);
		
	}

	public void onYesClicked(int position) {
		theOwner.deleteClickedItem(position);
	}

	public void onNoClicked(int position) {
		
	}

}
