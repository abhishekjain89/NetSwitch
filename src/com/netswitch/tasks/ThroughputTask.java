package com.netswitch.tasks;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.netswitch.helpers.ThroughputHelper;
import com.netswitch.listeners.ResponseListener;
import com.netswitch.models.Throughput;

public class ThroughputTask extends ServerTask{

	
	
	public ThroughputTask(Context context, Map<String, String> reqParams, 
			ResponseListener listener) {
		super(context, new HashMap<String, String>(), listener);
		
	}

	@Override
	public void runTask() {
		
		try {
			
			Throughput t = ThroughputHelper.getThroughput(getContext());
			getResponseListener().onCompleteThroughput(t);
			
		} catch (Exception e) {
			getResponseListener().onException(e);
		}
		
	}

	@Override
	public String toString() {
		return "ThroughputTask";
	}

	
}
