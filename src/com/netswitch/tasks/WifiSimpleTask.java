package com.netswitch.tasks;

import java.util.Map;

import android.content.Context;

import com.netswitch.listeners.ResponseListener;
import com.netswitch.models.Wifi;
import com.netswitch.utils.NeighborWifiUtil;
import com.netswitch.utils.WifiUtil;

public class WifiSimpleTask extends ServerTask{

	public WifiSimpleTask(Context context, Map<String, String> reqParams,
			ResponseListener listener) {
		super(context, reqParams, listener);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void runTask() {
		
		WifiUtil w = new WifiUtil();
		Wifi wifiList = w.getWifiSimple(this.getContext());
		
		this.getResponseListener().onCompleteWifi(wifiList);
		
	}

	@Override
	public String toString() {
		return "Wifi Task";
	}

}
