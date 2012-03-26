package com.netswitch.activities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

import com.netswitch.Values;
import com.netswitch.helpers.ServiceHelper;
import com.netswitch.helpers.ThreadPoolHelper;
import com.netswitch.helpers.UserDataHelper;
import com.netswitch.listeners.BaseResponseListener;
import com.netswitch.listeners.ResponseListener;
import com.netswitch.models.Battery;
import com.netswitch.models.Device;
import com.netswitch.models.GPS;
import com.netswitch.models.Measurement;
import com.netswitch.models.Model;
import com.netswitch.models.Network;
import com.netswitch.models.Ping;
import com.netswitch.models.Row;
import com.netswitch.models.Sim;
import com.netswitch.models.Throughput;
import com.netswitch.models.Usage;
import com.netswitch.models.Wifi;
import com.netswitch.services.PerformanceServiceAll;
import com.netswitch.tasks.InstallBinariesTask;
import com.netswitch.tasks.MeasurementTask;
import com.netswitch.tasks.SummaryTask;
import com.netswitch.ui.UIUtil;
import com.netswitch.ui.adapter.ItemAdapter;
import com.netswitch.utils.PreferencesUtil;
import com.netswitch.R;



public class UserFormActivity extends Activity 
{

	//private TableLayout table;

	private Activity activity;

	private Values session = null;

	private Button saveButton;
	private RadioGroup rGroup;
	final RadioButton[] rb = new RadioButton[5];
	UserDataHelper userhelp;
	Boolean force = false;
	int[] limit_val= {-1,0,250,500,750,1000,2000,9999};


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		Bundle extras = getIntent().getExtras();
		activity = this;
		userhelp = new UserDataHelper(activity);
		int old_cap = userhelp.getDataCap();
		
		
		
		try{
			force = extras.getBoolean("force");
		}
		catch (Exception e){
			force = false;
		}


		if(!force && userhelp.isFilled()){
			finish();
			Intent myIntent = new Intent(this, AnalysisActivity.class);
			startActivity(myIntent);
		}
		

		setContentView(R.layout.userform_screen);


		String[] limit_text = {"Dont have one","Dont know","250 MB","500 MB","750 MB","1 GB","2 GB","More than 2GB"};
		
		

		
		saveButton = (Button) this.findViewById(R.id.save);
		rGroup = (RadioGroup) findViewById(R.id.radio_group);

		LinearLayout.LayoutParams rg = new RadioGroup.LayoutParams(
				RadioGroup.LayoutParams.WRAP_CONTENT,50);

		for (int i = 0; i < limit_text.length; i++) {
			RadioButton radiobutton = new RadioButton(this);
			radiobutton.setTextColor(Color.BLACK);
			radiobutton.setTextSize(18);
			radiobutton.setText(limit_text[i]);
			if(old_cap == limit_val[i])
				radiobutton.setChecked(true);
			
			radiobutton.setId(i);
			rGroup.addView(radiobutton, rg);

		}



			saveButton.setOnClickListener(new OnClickListener()  {
				public void onClick(View v) {	

					int checkedRadioButton = rGroup.getCheckedRadioButtonId();

					if(checkedRadioButton<0) return;


					try{		
						userhelp.setBillingCycle(0);
						userhelp.setDataCap(limit_val[checkedRadioButton]);
					}
					catch(Exception e){
						e.printStackTrace();
					}

					finish();
					if(!force){
						Intent myIntent = new Intent(v.getContext(), AnalysisActivity.class);
						startActivity(myIntent);
					}

				}
			});


		}


}