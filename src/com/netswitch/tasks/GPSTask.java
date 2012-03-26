package com.netswitch.tasks;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.netswitch.helpers.DeviceHelper;
import com.netswitch.helpers.PingHelper;
import com.netswitch.listeners.ResponseListener;
import com.netswitch.models.GPS;
import com.netswitch.models.Measurement;
import com.netswitch.models.Ping;
import com.netswitch.utils.GPSUtil;
import com.netswitch.utils.HTTPUtil;
import com.netswitch.utils.GPSUtil.LocationResult;



public class GPSTask extends ServerTask{
	
	ResponseListener responseListener;
	static boolean isRunning = false;
	
	public GPSTask(Context context, Map<String, String> reqParams,ResponseListener listener) {
		super(context, reqParams, listener);
		
	}

	@Override
	public void runTask() {
		isRunning = true;
		GPSHandler.sendEmptyMessage(0);
		this.responseListener = this.getResponseListener();
		while(isRunning){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}


	private Handler GPSHandler = new Handler() {
		public void  handleMessage(Message msg) {
			try {
				boolean gps = GPSUtil.getLocation(getContext(), locationResult);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	public LocationResult locationResult = new LocationResult(){
        @Override
        public void gotLocation(final Location location){
        	GPS gps = new GPS();
            if (location != null)
            {
            	gps.setAltitude("" + location.getAltitude());
            	gps.setLatitude("" + location.getLatitude());
            	gps.setLongitude("" + location.getLongitude());
            	isRunning = false;
        		responseListener.onCompleteGPS(gps);
            }
            isRunning = false;
            gps = new GPS("Not Found","Not Found","Not Found");
            responseListener.onCompleteGPS(gps);
        }
    };
	
	@Override
	public String toString() {
		return "GPS Task";
	}
	

}
