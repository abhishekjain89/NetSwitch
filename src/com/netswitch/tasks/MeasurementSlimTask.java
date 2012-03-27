package com.netswitch.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.netswitch.Values;
import com.netswitch.helpers.MeasurementHelper;
import com.netswitch.helpers.ThreadPoolHelper;
import com.netswitch.listeners.BaseResponseListener;
import com.netswitch.listeners.FakeListener;
import com.netswitch.listeners.ResponseListener;
import com.netswitch.models.Address;
import com.netswitch.models.Battery;
import com.netswitch.models.Device;
import com.netswitch.models.GPS;
import com.netswitch.models.Measurement;
import com.netswitch.models.Network;
import com.netswitch.models.Ping;
import com.netswitch.models.Screen;
import com.netswitch.models.Sim;
import com.netswitch.models.Throughput;
import com.netswitch.models.Usage;
import com.netswitch.models.Wifi;
import com.netswitch.models.WifiNeighbor;
import com.netswitch.models.WifiPreference;
import com.netswitch.utils.GPSUtil;
import com.netswitch.utils.HTTPUtil;
import com.netswitch.utils.NeighborWifiUtil;
import com.netswitch.utils.SignalUtil;
import com.netswitch.utils.WifiUtil;
import com.netswitch.utils.GPSUtil.LocationResult;
import com.netswitch.utils.NeighborWifiUtil.NeighborResult;
import com.netswitch.utils.SignalUtil.SignalResult;

/*
 * Measurement Task 
 * set tasks to run and give ip address to ping and more
 * 
 * Call another task to backend
 * 
 * 
 */
public class MeasurementSlimTask extends ServerTask{
	
	ThreadPoolHelper serverhelper;
	
	Measurement measurement; 
	ArrayList<Ping> pings = new ArrayList<Ping>();
	

	public MeasurementSlimTask(Context context, ResponseListener listener) {
		super(context, new HashMap<String,String>(), listener);
		
		ThreadPoolHelper serverhelper = new ThreadPoolHelper(getValues().THREADPOOL_MAX_SIZE,getValues().THREADPOOL_KEEPALIVE_SEC);
	}
	
	public void killAll(){
		try{
		serverhelper.shutdown();
		}
		catch(Exception e){
			
		}
	}
	
	public void runTask() {

		measurement = new Measurement();
		
		Values session = this.getValues();
		ThreadPoolHelper serverhelper = new ThreadPoolHelper(session.THREADPOOL_MAX_SIZE,session.THREADPOOL_KEEPALIVE_SEC);
		
		ArrayList<Address> dsts = session.getPingServers();

		for(Address dst : dsts)
			serverhelper.execute(new PingTask(getContext(),new HashMap<String,String>(), dst, 5, new MeasurementListener()));
		
		
		measurement.setPings(pings);
		
		while(serverhelper.getThreadPoolExecutor().getActiveCount()>0){
			try {
				Thread.sleep(session.NORMAL_SLEEP_TIME);
			} catch (InterruptedException e) {
				this.killAll();
				return;	
			}
		}
		

		getResponseListener().onCompleteMeasurement(measurement);
		serverhelper.execute(new ThroughputTask(getContext(),new HashMap<String,String>(), new MeasurementListener()));
		
		try {
			Thread.sleep(session.NORMAL_SLEEP_TIME);
		} catch (InterruptedException e) {
			this.killAll();
			return;
		}
		
		while(serverhelper.getThreadPoolExecutor().getActiveCount()>0){
			try {
				Thread.sleep(session.NORMAL_SLEEP_TIME);
			} catch (InterruptedException e) {
				this.killAll();
				return;
			}

		}
		
		

	}

	@Override
	public String toString() {
		return "Measurement Task";
	}


	public class MeasurementListener extends BaseResponseListener{

		public void onCompletePing(Ping response) {
			pings.add(response);
			
		}

		public void onComplete(String response) {

		} 

		public void onCompleteMeasurement(Measurement response) {
			getResponseListener().onCompleteMeasurement(response);
		}

		public void onCompleteDevice(Device response) {
			getResponseListener().onCompleteDevice(response);

		}
 
		public void onUpdateProgress(int val) {
			// TODO Auto-generated method stub

		}

		public void onCompleteGPS(GPS gps) {
			measurement.setGps(gps);
			getResponseListener().onCompleteGPS(gps);

		}

		public void makeToast(String text) {
			getResponseListener().makeToast(text);

		}

		public void onCompleteSignal(String signalStrength) {
			
			Network network = measurement.getNetwork();
			int i = 100;
			while(network == null) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				network = measurement.getNetwork();
				if (i-- == 0) {
					break;
				}
			}
			network.setSignalStrength("" + signalStrength);
			measurement.setNetwork(network);
		}
		public void onCompleteUsage(Usage usage) {
			measurement.setUsage(usage);
			getResponseListener().onCompleteUsage(usage);

		}

		public void onCompleteThroughput(Throughput throughput) {
			measurement.setThroughput(throughput);
			getResponseListener().onCompleteThroughput(throughput);


		}

		public void onCompleteWifi(Wifi wifi) {		
			//if (wifiRunning)
			//{
			//	wifiRunning = false;
			measurement.setWifi(wifi);
			getResponseListener().onCompleteWifi(wifi);
			//}
		}

		public void onCompleteNetwork(Network network) {
			getResponseListener().onCompleteNetwork(network);

		}

		public void onCompleteSIM(Sim sim) {
			getResponseListener().onCompleteSIM(sim);

		}

		public void onCompleteBattery(Battery response) {
			measurement.setBattery(response);
			getResponseListener().onCompleteBattery(response);

		}

		public void onCompleteSummary(JSONObject Object) {
			// TODO Auto-generated method stub
			
		}

		public void onFail(String response) {
			// TODO Auto-generated method stub
			
		}
	}



}
