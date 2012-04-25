package com.netswitch.listeners;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.net.wifi.ScanResult;

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



public class FakeListener extends BaseResponseListener{

	public void onCompletePing(Ping response) {

	}

	public void onComplete(String response) {

	}

	public void onCompleteMeasurement(Measurement response) {
	}

	public void onCompleteDevice(Device response) {
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
	
	public void onFail(String response){
		
	}
	
	public void makeToast(String text) {
		// TODO Auto-generated method stub
		
	}

	public void onCompleteSignal(String signalStrength) {
		
	}
	
	public void onCompleteThroughput(Throughput throughput) {

		// TODO Auto-generated method stub
		
	}

	public void onCompleteWifi(Wifi wifi) {
		// TODO Auto-generated method stub
		
	}

	public void onCompleteNetwork(Network network) {
		// TODO Auto-generated method stub
		
	}

	public void onCompleteSIM(Sim sim) {
		// TODO Auto-generated method stub
		
	}

	public void onCompleteBattery(Battery response) {
		// TODO Auto-generated method stub
		
	}

	public void onCompleteSummary(JSONObject Object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCompleteJob(Measurement measurement) {
		// TODO Auto-generated method stub
		
	}

}


