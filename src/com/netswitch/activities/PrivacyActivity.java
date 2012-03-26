package com.netswitch.activities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

import com.netswitch.Values;
import com.netswitch.helpers.ServiceHelper;
import com.netswitch.helpers.ThreadPoolHelper;
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


public class PrivacyActivity extends Activity 
{
	
	//private TableLayout table;

	private Activity activity;
	private ThreadPoolHelper serverhelper;
	private Values session = null;
	private Button acceptButton;
	private Button rejectButton;
	public String serviceTag = "PerformanceService";
	
	public static final String SETTINGS_FILE_NAME = "PingSettings";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		if(PreferencesUtil.isAccepted(this)){
			finish();
			System.out.println("ACCEPT");
			Intent myIntent = new Intent(this, UserFormActivity.class);
            startActivity(myIntent);
		}
		
		setContentView(R.layout.privacy_screen);
		
		activity = this;
				
		serverhelper = new ThreadPoolHelper(5,10);
		
		acceptButton = (Button) findViewById(R.id.accept);
		rejectButton = (Button) findViewById(R.id.reject);
		
		rejectButton.setOnClickListener(new OnClickListener()  {
			public void onClick(View v) {	

				finish();
			}
		});
		
		acceptButton.setOnClickListener(new OnClickListener()  {
			public void onClick(View v) {	
				finish();
				PreferencesUtil.acceptConditions(activity);
				
				Intent myIntent = new Intent(v.getContext(), UserFormActivity.class);
                startActivity(myIntent);
				
			}
		});
		

	}	
	
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if (intent!=null){
	        Bundle extras = intent.getExtras();
	        //tv.setText(extras!=null ? extras.getString("returnKey") : "empty");
        }
    }
	
	
}