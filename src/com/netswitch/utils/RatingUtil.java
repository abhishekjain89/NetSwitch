package com.netswitch.utils;

import com.netswitch.models.Measurement;
import com.netswitch.models.Row;
import com.netswitch.models.Throughput;

public class RatingUtil {

	Measurement measure;

	int throughputScore = 30;
	int latencyScore = 20;
	int packetlossScore = 20;
	int signalstrenthScore = 20;

	public RatingUtil(Measurement measure){
		this.measure = measure;
	}
	
	public int getRating(){
		
		int total = 0;
		
		total+=getSignalStrengthScore();
		total+=getThroughputScore();
		total+=getLatencyScore();
		
		return total;
	}


	public boolean currentConnectionMobile(){

		if(!measure.getNetwork().getNetworkType().equals("Wifi")) return true;
		return false;
	}

	public boolean currentConnectionWifi(){

		if(measure.getNetwork().getNetworkType().equals("Wifi")) return true;
		return false;
	}

	public int getSignalStrengthScore(){

		int score = 0;
		
		if(measure.getWifi()!=null){
			if(measure.getWifi().strength>1){
				score= measure.getWifi().strength*10;
			}
		}
		else if(measure.getNetwork()!=null){
			if(Integer.parseInt(measure.getNetwork().getSignalStrength())>1){
				int percent=DeviceUtil.getSignalPercentage(Integer.parseInt(measure.getNetwork().getSignalStrength()),measure.getNetwork().getNetworkType());
				score= percent;
			}
		}

		return score*signalstrenthScore/100;
	}
	
	public int getLatencyScore(){
		return 0;
	}

	public int getThroughputScore(){

		Throughput throughput = measure.getThroughput();

		int value = (int) throughput.getDownLink().speedInBits();

		int score = 0;

		if(value>10000){
			score = 100;
		}
		else if(value>5000){
			score = 70;
		}
		else if(value>2500){
			score = 50;
		}
		else if(value>1000){
			score = 30;
		}
		else if(value>500){
			score = 15;
		}


		return score*throughputScore/100;

	}

}
