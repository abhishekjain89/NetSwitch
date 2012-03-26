package com.netswitch.listeners;

import java.io.FileNotFoundException;
import java.io.IOException;
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


public interface ResponseListener {

    public void onComplete(String response);
    
    public void onCompletePing(Ping response);
    
    public void onCompleteMeasurement(Measurement response);
    
    public void onCompleteDevice(Device response);
    
    public void onIOException(IOException e);


    public void onFileNotFoundException(FileNotFoundException e);

    public void onCompleteBattery(Battery response);
    
    public void onException(Exception e);
    
    public void onUpdateProgress(int val);

	public void onCompleteGPS(GPS gps);
	
	public void onCompleteUsage(Usage usage);
	
	public void onCompleteThroughput(Throughput throughput);

	public void makeToast(String text);

	public void onCompleteSignal(String signalStrength);

	public void onCompleteWifi(Wifi wifiList);

	public void onCompleteNetwork(Network network);

	public void onCompleteSIM(Sim sim);
	
	public void onFail(String response);
	
	public void onCompleteSummary(JSONObject Object);

	

}