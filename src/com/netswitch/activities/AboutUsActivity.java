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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

import com.netswitch.Values;
import com.netswitch.helpers.ServiceHelper;
import com.netswitch.helpers.ThreadPoolHelper;
import com.netswitch.helpers.UserDataHelper;
import com.netswitch.listeners.BaseResponseListener;
import com.netswitch.listeners.FakeListener;
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
import com.netswitch.tasks.ValuesTask;
import com.netswitch.ui.UIUtil;
import com.netswitch.ui.adapter.ItemAdapter;
import com.netswitch.utils.PreferencesUtil;
import com.netswitch.R;



public class AboutUsActivity extends Activity 
{

	//private TableLayout table;

	private Activity activity;

	private Values session = null;

	private Button backButton;
	
	private Button increment;
	private Button decrement;
	private ListView listview;
	Boolean force = false;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.about_us);

		activity = this;
		
		
		backButton = (Button) this.findViewById(R.id.back);
		listview = (ListView) findViewById(R.id.allview);
		
		ThreadPoolHelper serverhelper = new ThreadPoolHelper(10,30);

		serverhelper.execute(new SummaryTask(this,new Listener()));


		

		backButton.setOnClickListener(new OnClickListener()  {
			public void onClick(View v) {	

				finish();

			}
		});


	}	

	public static int forceLimits(int val){
		if(val<1) val=1;
		if(val>30) val=30;
		return val;
	}
	
	public class Listener extends BaseResponseListener{

		public void onComplete(String response) {
			// TODO Auto-generated method stub
			
		}

		public void onCompletePing(Ping response) {
			// TODO Auto-generated method stub
			
		}

		public void onCompleteMeasurement(Measurement response) {
			// TODO Auto-generated method stub
			
		}

		public void onCompleteDevice(Device response) {
			// TODO Auto-generated method stub
			
		}

		public void onCompleteBattery(Battery response) {
			// TODO Auto-generated method stub
			
		}

		public void onUpdateProgress(int val) {
			// TODO Auto-generated method stub
			
		}

		public void onCompleteGPS(GPS gps) {
			// TODO Auto-generated method stub
			
		}

		public void onCompleteUsage(Usage usage) {
			// TODO Auto-generated method stub
			
		}

		public void onCompleteThroughput(Throughput throughput) {
			// TODO Auto-generated method stub
			
		}

		public void makeToast(String text) {
		}

		public void onCompleteSignal(String signalStrength) {
			// TODO Auto-generated method stub
			
		}

		public void onCompleteWifi(Wifi wifiList) {
			// TODO Auto-generated method stub
			
		}

		public void onCompleteNetwork(Network network) {
			// TODO Auto-generated method stub
			
		}

		public void onCompleteSIM(Sim sim) {
			// TODO Auto-generated method stub
			
		}

		public void onCompleteSummary(JSONObject Object) {
			Message msg=Message.obtain(UIHandler, 0, Object);
			UIHandler.sendMessage(msg);
			
		}

		public void onFail(String response) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onCompleteJob(Measurement measurement) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private Handler UIHandler = new Handler(){
		public void  handleMessage(Message msg) {
			JSONObject obj = (JSONObject)msg.obj;
			
	 		ArrayList<Row> cells = new ArrayList<Row>();
			
	 		try {
	 			cells.add(new Row("Project Statistics"));
				cells.add(new Row("Total Apps",""+obj.get("total-apps")));
				cells.add(new Row("Total Devices",""+obj.get("total-devices")));
				cells.add(new Row("Total CellTowers",""+obj.get("total-cells")));
				cells.add(new Row("Total Wifi-spots",""+obj.get("total-wifis")));
				cells.add(new Row(R.layout.cell_view_title_banner,"Status: " + obj.get("status")));
				
				if(cells.size()!=0){
					ItemAdapter itemadapter = new ItemAdapter(activity,cells);
					for(Row cell: cells)
						itemadapter.add(cell);
					listview.setAdapter(itemadapter);


					itemadapter.notifyDataSetChanged();
					UIUtil.setListViewHeightBasedOnChildren(listview,itemadapter);
				}
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	};
	

}