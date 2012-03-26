package com.netswitch.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.netswitch.Values;
import com.netswitch.helpers.ThreadPoolHelper;
import com.netswitch.listeners.BaseResponseListener;
import com.netswitch.listeners.FakeListener;
import com.netswitch.listeners.ResponseListener;
import com.netswitch.models.Battery;
import com.netswitch.models.Device;
import com.netswitch.models.GPS;
import com.netswitch.models.Measurement;
import com.netswitch.models.Network;
import com.netswitch.models.Ping;
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

/*
 * Measurement Task 
 * set tasks to run and give ip address to ping and more
 * 
 * Call another task to backend
 * 
 * 
 */
public class ValuesTask extends ServerTask{

	public ValuesTask(Context context,
			ResponseListener listener) {
		super(context, new HashMap<String,String>(), listener);


	}

	public void runTask() {

		HTTPUtil http = new HTTPUtil();

		try {
			String output = http.request(this.getReqParams(), "GET", "values", "", "".toString());
			JSONObject object = new JSONObject(output);
			try{
				getValues().insertValues(object.getJSONObject("values"));
				getValues().loadValues();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public String toString() {
		return "Values Task";
	}
}