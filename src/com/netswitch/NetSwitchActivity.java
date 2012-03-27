package com.netswitch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.json.JSONObject;

import com.netswitch.activities.AnalysisActivity.Listener;
import com.netswitch.helpers.ThreadPoolHelper;
import com.netswitch.listeners.BaseResponseListener;
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
import com.netswitch.ui.UIUtil;
import com.netswitch.ui.adapter.ItemAdapter;
import com.netswitch.utils.DeviceUtil;
import com.netswitch.utils.WifiSwitchUtil;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NetSwitchActivity extends Activity {
	/** Called when the activity is first created. */
	final String TAG = "getMethodFromClass";
	boolean currentstate = false;
	TextView current_conn;
	TextView advised;
	Button wifiOn;
	Button wifiOff;
	Button mobile_settings;
	private ThreadPoolHelper serverhelper;
	Activity activity;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		activity = this;
		current_conn = (TextView) findViewById(R.id.current);
		advised = (TextView) findViewById(R.id.advised);
		wifiOn = (Button) findViewById(R.id.wifi_on);
		wifiOff = (Button) findViewById(R.id.wifi_off);
		mobile_settings = (Button) findViewById(R.id.mobile_settings);

		serverhelper = new ThreadPoolHelper(10,30);

		UpdateValues();


		wifiOn.setOnClickListener(new OnClickListener()  {
			public void onClick(View v) {	
				WifiSwitchUtil.changeWifiState(true,activity);
			}
		});

		wifiOff.setOnClickListener(new OnClickListener()  {
			public void onClick(View v) {	
				WifiSwitchUtil.changeWifiState(false,activity);
			}
		});
		
		mobile_settings.setOnClickListener(new OnClickListener()  {
			public void onClick(View v) {	
				
			}
		});
		
		
	}

	public void UpdateValues(){
		serverhelper.execute(new MobileNetworkTask(this,new Listener()));
	}

	private Handler UIHandler = new Handler(){
		public void  handleMessage(Message msg) {
			Measurement measure = (Measurement)msg.obj;

			ArrayList<Row> cells = new ArrayList<Row>();

			try {

				current_conn.setText(measure.getNetwork().getConnectionType());
				String advised_text = "Mobile";
				if(measure.getWifi()!=null)
	 				if(measure.getWifi().strength>1)
	 					if(measure.getWifi().strength>5) advised_text = "Wifi";

				advised.setText(advised_text);

			} catch (Exception e) {
				e.printStackTrace();
			}


		}
	};

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

}