package com.netswitch.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.netswitch.utils.SHA1Util;
import com.netswitch.R;

import android.content.Context;

public class Measurement implements MainModel{
	
	
	ArrayList<Ping> pings; 
	Device device; 
	Network network; 
	Sim sim; 
	Throughput throughput;
	ArrayList<Screen> screens = new ArrayList<Screen>();
	boolean isManual = false;
	

	private static String DESCRIPTION = "Details of delay in milliseconds experienced on the network for the different servers";

	public String getDescription() {
		return DESCRIPTION;
	}
	
	public boolean isManual() {
		return isManual;
	}

	public void setManual(boolean isManual) {
		this.isManual = isManual;
	}

	public ArrayList<Screen> getScreens() {
		return screens;
	}

	public void setScreens(ArrayList<Screen> screens) {
		this.screens = screens;
	}

	GPS gps;
	State state;
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	String time;
	String localTime;
	public String getLocalTime() {
		return localTime;
	}

	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}

	String deviceId;
	Usage usage;
	Battery battery;
	Wifi wifi;

	public Wifi getWifi() {
		return wifi;
	}

	public void setWifi(Wifi wifi) {
		this.wifi = wifi;
	}

	public Battery getBattery() {
		return battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}

	public Usage getUsage() {
		return usage;
	}

	public void setUsage(Usage usage) {
		this.usage = usage;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public Sim getSim() {
		return sim;
	}

	public void setSim(Sim sim) {
		this.sim = sim;
	}

	public Throughput getThroughput() {
		return throughput;
	}

	public void setThroughput(Throughput throughput) {
		this.throughput = throughput;
	}
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public ArrayList<Ping> getPings() {
		return pings;
	}

	public void setPings(ArrayList<Ping> pings) {
		this.pings = pings;
	}

	public Measurement() {
		throughput = new Throughput();
		device = new Device();
		gps = new GPS();
	}
	
	public GPS getGps() {
		return gps;
	}

	public void setGps(GPS gps) {
		this.gps = gps;
	}
	
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		try {
			
			JSONArray array = new JSONArray();
			for(Ping p: pings){
				array.put(p.toJSON());
			}
			
			putSafe(obj,"pings", array);
			
			JSONArray array2 = new JSONArray();
			for(Screen s: screens){
				array2.put(s.toJSON());
			}
			
			putSafe(obj,"screens", array2);
			putSafe(obj,"deviceid", SHA1Util.SHA1(deviceId));
			putSafe(obj,"time", time);	
			putSafe(obj,"localtime",localTime);
			putSafe(obj,"device",device.toJSON());
			putSafe(obj,"throughput",throughput.toJSON());
			putSafe(obj,"gps",gps.toJSON());
			putSafe(obj,"battery", battery.toJSON());
			putSafe(obj,"usage",usage.toJSON());
			putSafe(obj,"network",network.toJSON());
			putSafe(obj,"sim",sim.toJSON());
			putSafe(obj,"wifi", wifi.toJSON());
			putSafe(obj,"state",state.toJSON());
			if(isManual)
				putSafe(obj, "isManual", 1);
			else
				putSafe(obj, "isManual", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public void putSafe(JSONObject obj,String key,Object text){
		 
		try {
			obj.put(key,text);
		} catch (JSONException e) {
			
		}
	}

	public String getTitle() {
		
		return "Pings";
	}
	
	public ArrayList<Row> getDisplayData(){
		ArrayList<Row> data = new ArrayList<Row>();
		data.add(new Row("Latency (Avg,Max,Min,Std)"));
		for(Ping p: pings){
			ArrayList<String> str = new ArrayList<String>();
			str.add(""+(int)p.measure.getAverage());
			str.add(""+(int)p.measure.getMax());
			str.add(""+(int)p.measure.getMin());
			str.add(""+(int)p.measure.getStddev());
			data.add(new Row(p.getDst().getTagname(),str));
		}
		
		return data;
	}
	
	public int getIcon() {

		return R.drawable.png;
	}


}
