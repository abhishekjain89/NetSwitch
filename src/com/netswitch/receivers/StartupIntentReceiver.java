package com.netswitch.receivers;

import java.util.HashMap;

import com.netswitch.helpers.ServiceHelper;
import com.netswitch.helpers.ThreadPoolHelper;
import com.netswitch.listeners.BaseResponseListener;
import com.netswitch.listeners.FakeListener;
import com.netswitch.models.Device;
import com.netswitch.models.Measurement;
import com.netswitch.models.Ping;
import com.netswitch.tasks.DeviceTask;
import com.netswitch.tasks.InstallBinariesTask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

public class StartupIntentReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		Log.v("startup","Starting Intent Receiver");

		/* INSTALL BINARIES*/

		ThreadPoolHelper serverhelper = new ThreadPoolHelper(10,30);

		//serverhelper.execute(new InstallBinariesTask(context,new HashMap<String,String>(), new String[0], new FakeListener()));
		try {
			Thread.sleep(250);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*while(serverhelper.getThreadPoolExecutor().getActiveCount()>0){
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}

			Log.v(this.toString(),"Installing Binaries...");
		}*/
		Log.v(this.toString(),"Binaries Installed");
		/*   START PERFORMANCE SERVICE */
		ServiceHelper.processStartService(context);
		 
	}


	

	public String toString(){
		return "StartIntentReceiver";
	}

}