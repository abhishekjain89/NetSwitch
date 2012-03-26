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
import com.netswitch.models.State;
import com.netswitch.models.Throughput;
import com.netswitch.models.Usage;
import com.netswitch.models.Wifi;
import com.netswitch.models.WifiNeighbor;
import com.netswitch.models.WifiPreference;
import com.netswitch.utils.DeviceUtil;
import com.netswitch.utils.GPSUtil;
import com.netswitch.utils.HTTPUtil;
import com.netswitch.utils.NeighborWifiUtil;
import com.netswitch.utils.SignalUtil;
import com.netswitch.utils.StateUtil;
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
public class ParameterTask extends ServerTask{

	ThreadPoolHelper serverhelper;


	public ParameterTask(Context context,
			ResponseListener listener) {
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

		HTTPUtil http = new HTTPUtil();

		try {

			StateUtil util = new StateUtil(getContext());
			State state = util.createState();
			
			if(state.getCellId().equals("65535")){
				getResponseListener().onComplete("true");
				return;
			}
			

			Log.v(toString(),state.toJSON().toString());
			String output = http.request(this.getReqParams(), "POST", "parameter_check", "", state.toJSON().toString());

			if(output.contains("1"))
				getResponseListener().onComplete("true");
			else
				getResponseListener().onFail("true");


		} catch (Exception e) {

			getResponseListener().onComplete("true");
			e.printStackTrace();
		}


	}

	@Override
	public String toString() {
		return "Paramater Task";
	}
}