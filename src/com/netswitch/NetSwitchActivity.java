package com.netswitch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.netswitch.utils.WifiSwitchUtil;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NetSwitchActivity extends Activity {
	/** Called when the activity is first created. */
	 final String TAG = "getMethodFromClass";
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Button wifi_button = (Button) findViewById(R.id.wifi_switch);
		wifi_button.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				Context mContext = getApplicationContext();
				
				boolean currentstate;
				try{
					currentstate = WifiSwitchUtil.getWifiState(mContext);
				}
				catch (NullPointerException e)//If WIFI state doesn't return a value, sets the WIFI state to true
				{
					currentstate = false;
				}
				WifiSwitchUtil.changeWifiState(!currentstate, mContext);

			}    
		});

	}
}