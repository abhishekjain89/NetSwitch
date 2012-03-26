package com.netswitch.tasks;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

import com.netswitch.helpers.DeviceHelper;
import com.netswitch.helpers.PingHelper;
import com.netswitch.listeners.ResponseListener;
import com.netswitch.models.Address;
import com.netswitch.models.Measurement;
import com.netswitch.models.Ping;
import com.netswitch.utils.HTTPUtil;

/*
 * Measurement Task 
 * set tasks to run and give ip address to ping and more
 * 
 * Call another task to backend
 * 
 * 
 */
public class PingTask extends ServerTask{
	Address dst;
	int count;
	public PingTask(Context context, Map<String, String> reqParams, Address dst, int count,
			ResponseListener listener) {
		super(context, reqParams, listener);
		this.dst  = dst;
		this.count = count;
	}

	@Override
	public void runTask() {
		
		Ping ping = PingHelper.pingHelp(dst, count);
		this.getResponseListener().onCompletePing(ping);
	}

	@Override
	public String toString() {
		return "Ping Task";
	}
	

}
