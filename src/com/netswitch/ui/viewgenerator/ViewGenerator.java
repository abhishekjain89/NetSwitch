package com.netswitch.ui.viewgenerator;

import java.util.Map;

import com.netswitch.listeners.ResponseListener;
import com.netswitch.models.Model;
import com.netswitch.models.Row;
import com.netswitch.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public abstract class ViewGenerator {
	
	
	private int resourceID;
	
	public ViewGenerator(int resource) {
		
		this.resourceID = resource;
		
	}
	public int getResourceID(){
		return resourceID;
	}
	public View generateViewHolder(LayoutInflater inflater){
		View view = inflater.inflate(getResourceID(), null);
		
		ViewHolder holder = fillViewHolder(view,inflater);
		
		view.setTag(holder);
		return view;
	}
	public abstract ViewHolder fillViewHolder(View view,LayoutInflater inflater);
	
	public abstract void populateView(Row item);
	
	

}
