package com.netswitch.activities;


import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.netswitch.Values;
import com.netswitch.activities.AboutUsActivity.Listener;
import com.netswitch.helpers.ServiceHelper;
import com.netswitch.helpers.ThreadPoolHelper;
import com.netswitch.listeners.BaseResponseListener;
import com.netswitch.listeners.FakeListener;
import com.netswitch.models.Battery;
import com.netswitch.models.Device;
import com.netswitch.models.GPS;
import com.netswitch.models.Measurement;
import com.netswitch.models.Network;
import com.netswitch.models.Ping;
import com.netswitch.models.Row;
import com.netswitch.models.Sim;
import com.netswitch.models.Throughput;
import com.netswitch.models.Usage;
import com.netswitch.models.Wifi;
import com.netswitch.tasks.MobileNetworkTask;
import com.netswitch.tasks.SummaryTask;
import com.netswitch.tasks.ValuesTask;
import com.netswitch.ui.UIUtil;
import com.netswitch.ui.adapter.ItemAdapter;
import com.netswitch.utils.DeviceUtil;
import com.netswitch.R;



public class AnalysisActivity extends Activity 
{

	//private TableLayout table;
	private LinearLayout table;
	private Button testButton;
	private Button settingsButton;
	private Button aboutusButton;
	private ListView listview;
	private TextView apptext;
	private TextView current_conn;
	private TextView devicetext;
	//private TextView tv;
	private Activity activity;
	private ThreadPoolHelper serverhelper;
	private Values session = null;
	




	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);


		setContentView(R.layout.main_screen);

		activity = this;
		session = (Values) getApplicationContext();
		session.loadValues();
		

		serverhelper = new ThreadPoolHelper(5,10);
		listview = (ListView) findViewById(R.id.allview);
		testButton=(Button)findViewById(R.id.test);
		settingsButton=(Button)findViewById(R.id.settings);
		current_conn=(TextView)findViewById(R.id.current);
		aboutusButton=(Button)findViewById(R.id.aboutus);

		ThreadPoolHelper serverhelper = new ThreadPoolHelper(10,30);

		serverhelper.execute(new ValuesTask(this,new FakeListener()));

		ServiceHelper.processStopService(this);
		ServiceHelper.processStartService(this);

		serverhelper = new ThreadPoolHelper(10,30);

		serverhelper.execute(new MobileNetworkTask(this,new Listener()));


		testButton.setOnClickListener(new OnClickListener()  {
			public void onClick(View v) {	

				ServiceHelper.processStopService(activity);

				Intent myIntent = new Intent(v.getContext(), RunActivity.class);
				startActivity(myIntent);
			}
		});

		settingsButton.setOnClickListener(new OnClickListener()  {
			public void onClick(View v) {	
				Intent myIntent = new Intent(v.getContext(), UserFormActivity.class);
				myIntent.putExtra("force",true);
				startActivity(myIntent);
			}
		});

		aboutusButton.setOnClickListener(new OnClickListener()  {
			public void onClick(View v) {	

				Intent myIntent = new Intent(v.getContext(), AboutUsActivity.class);
				startActivity(myIntent);
			}
		});


	}	

	public class Listener extends BaseResponseListener{

		public void onComplete(String response) {
			// TODO Auto-generated method stub

		}

		public void onCompletePing(Ping response) {
			// TODO Auto-generated method stub

		}

		public void onCompleteMeasurement(Measurement response) {
			Message msg=Message.obtain(UIHandler, 0, response);
			UIHandler.sendMessage(msg);

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
			

		}

		public void onFail(String response) {
			// TODO Auto-generated method stub

		}

	}
	
	private Handler UIHandler = new Handler(){
		public void  handleMessage(Message msg) {
			Measurement measure = (Measurement)msg.obj;
			
	 		ArrayList<Row> cells = new ArrayList<Row>();
			
	 		try {
	 			
	 			current_conn.setText(measure.getNetwork().getConnectionType());
	 			
	 			cells.add(new Row("Status"));
	 			if(measure.getWifi()!=null)
	 				if(measure.getWifi().strength>1)
	 					cells.add(new Row("Wifi",measure.getWifi().strength*10));
	 				else
	 					cells.add(new Row("Wifi","Not Connected"));
	 			else
 					cells.add(new Row("Wifi","Not Connected"));
	 			
	 			
	 			if(measure.getNetwork()!=null)
	 				if(Integer.parseInt(measure.getNetwork().getSignalStrength())>1){
	 					int percent=DeviceUtil.getSignalPercentage(Integer.parseInt(measure.getNetwork().getSignalStrength()),measure.getNetwork().getNetworkType());
	 					cells.add(new Row("Mobile",percent));
	 				}
	 				else
	 					cells.add(new Row("Mobile","Not Connected"));
	 			else
 					cells.add(new Row("Mobile","Not Connected"));
				
				if(cells.size()!=0){
					ItemAdapter itemadapter = new ItemAdapter(activity,cells);
					for(Row cell: cells)
						itemadapter.add(cell);
					listview.setAdapter(itemadapter);


					itemadapter.notifyDataSetChanged();
					UIUtil.setListViewHeightBasedOnChildren(listview,itemadapter);
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	};

}