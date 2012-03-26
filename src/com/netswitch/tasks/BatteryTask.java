package com.netswitch.tasks;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

import com.netswitch.helpers.DeviceHelper;
import com.netswitch.helpers.PingHelper;
import com.netswitch.listeners.ResponseListener;
import com.netswitch.models.Measurement;
import com.netswitch.models.Ping;
import com.netswitch.utils.BatteryUtil;
import com.netswitch.utils.HTTPUtil;
import com.netswitch.utils.SignalUtil;

/*
 * Measurement Task 
 * set tasks to run and give ip address to ping and more
 * 
 * Call another task to backend
 * 
 * 
 */
public class BatteryTask extends ServerTask{
	
	public BatteryTask(Context context, Map<String, String> reqParams, ResponseListener listener) {
		super(context, reqParams, listener);
	}
 
	@Override
	public void runTask() {
		Measurement m = new Measurement();
		BatteryUtil b = new BatteryUtil();
		b.getBattery(getContext(),m);
		this.getResponseListener().onCompleteBattery(m.getBattery());
		
	}

	@Override
	public String toString() {
		return "Device Task";
	}
	

}
