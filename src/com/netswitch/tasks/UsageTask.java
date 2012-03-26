package com.netswitch.tasks;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.netswitch.helpers.AppUsageHelper;
import com.netswitch.helpers.DeviceHelper;
import com.netswitch.helpers.PingHelper;
import com.netswitch.listeners.ResponseListener;
import com.netswitch.models.Measurement;
import com.netswitch.models.Ping;
import com.netswitch.models.Usage;
import com.netswitch.utils.DeviceUtil;
import com.netswitch.utils.HTTPUtil;
import com.netswitch.utils.SHA1Util;

/*
 * Measurement Task 
 * set tasks to run and give ip address to ping and more
 * 
 * Call another task to backend
 * 
 * 
 */
public class UsageTask extends ServerTask{
	String dstIp;
	int count;
	boolean getAll;
	
	public UsageTask(Context context, Map<String, String> reqParams, boolean getAll,
			ResponseListener listener) {
		super(context, reqParams, listener);
		this.getAll = getAll;
	}

	@Override
	public void runTask() {
		
		Usage usage = AppUsageHelper.getUsageData(getContext());
		
		
		
		getResponseListener().onCompleteUsage(usage);
		
	}

	@Override
	public String toString() {
		return "Usage Task";
	}
	

}
