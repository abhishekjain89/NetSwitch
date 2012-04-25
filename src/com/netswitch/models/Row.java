package com.netswitch.models;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.netswitch.ui.viewgenerator.IconKeyProgressViewGenerator;
import com.netswitch.ui.viewgenerator.KeyFourValueViewGenerator;
import com.netswitch.ui.viewgenerator.KeyIconProgressViewGenerator;
import com.netswitch.ui.viewgenerator.KeyProgressViewGenerator;
import com.netswitch.ui.viewgenerator.KeyValueColorViewGenerator;
import com.netswitch.ui.viewgenerator.KeyValueViewGenerator;

import com.netswitch.ui.viewgenerator.TitleViewGenerator;
import com.netswitch.ui.viewgenerator.ViewGenerator;
import com.netswitch.R;

public class Row {

	public String first="";
	public String second="";
	public int value=0;
	public double valueOne = 0;
	public double valueTwo = 0;
	public Drawable image;
	public int imageResourceID;
	public ArrayList<String> seconds;
	public int color = 0;

	ViewGenerator viewgen;

	public Row(String first){
		this.first = first;

		viewgen = new TitleViewGenerator(R.layout.cell_view_title);
	}

	public Row(String first,String second){
		this(first);

		this.second = second;

		viewgen = new KeyValueViewGenerator(R.layout.cell_view_keyvalue);
	}

	public Row(int resourceid,String first){
		this(first);

		viewgen = new TitleViewGenerator(resourceid);
	}

	public Row(String first,ArrayList<String> seconds){
		this(first);

		this.seconds = seconds;

		viewgen = new KeyFourValueViewGenerator(R.layout.cell_view_keyfourvalue);
	}

	public Row(String first,int value){
		this(first,value+" %",value);
		viewgen = new KeyProgressViewGenerator(R.layout.cell_view_keyprogress);
	}

	public Row(String first,String second,int value){
		this(first,second);
		this.value = value;
		viewgen = new KeyProgressViewGenerator(R.layout.cell_view_keyprogress);
	}


	public Row(Drawable icon,String first,String second,int value){
		this(first,second,value);
		this.image = icon;

		viewgen = new IconKeyProgressViewGenerator(R.layout.cell_view_iconkeyprogress);
	}

	public Row(String first,int imageid,int value){
		this(first,value);
		this.imageResourceID = imageid;
		viewgen = new KeyIconProgressViewGenerator(R.layout.cell_view_keyiconprogress);
	}

	public Row(String key, String text, int resourceid,int color){
		this(key,text);
		this.imageResourceID = resourceid;
		this.color =color;
		viewgen = new KeyValueColorViewGenerator(resourceid);
	}

	public ViewGenerator getViewGenerator(){
		return viewgen;
	}


}
