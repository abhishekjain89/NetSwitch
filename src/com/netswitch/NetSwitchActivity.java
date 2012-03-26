package com.netswitch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
				WifiManager mWifi = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);
				mWifi.setWifiEnabled(true);

			}    
		});

		final Button network_button = (Button) findViewById(R.id.network_switch);
		network_button.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				Context mContext = getApplicationContext();

				WifiManager mWifi = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);
				mWifi.setWifiEnabled(false);
				
				Context mContext1 = getApplicationContext();
				//ConnectivityManager connectivityManager =(ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE); 
				//NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
				boolean enable=true;
				ConnectivityManager cm = (ConnectivityManager) mContext1 .getSystemService(Activity.CONNECTIVITY_SERVICE);
				Method m = getMethodFromClass(cm, "setMobileDataEnabled");
				runMethodofClass(cm, m, enable);
			}
			
			private Method getMethodFromClass(Object obj, String methodName) {
			 
			    Class<?> whichClass = null;
			    try {
			        whichClass = Class.forName(obj.getClass().getName());
			     } catch (ClassNotFoundException e2) {
			         Log.d(TAG, "class not found");
			     }
			     Method method = null;
			     try {
			        //method = whichClass.getDeclaredMethod(methodName);
			        Method[] methods = whichClass.getDeclaredMethods();
			        for (Method m : methods) {
			            if (m.getName().contains(methodName)) {
			                method = m;
			            }
			        }
			     } catch (SecurityException e2) {
			       Log.d(TAG, "SecurityException for " + methodName);
			      }
			      return method;
			}
			
			private Object runMethodofClass(Object obj, Method method, Object... argv) {
			    Object result = null;
			    if (method == null) return result;
			    method.setAccessible(true);
			    try {
			       result = method.invoke(obj, argv);
			    } catch (IllegalArgumentException e) {
			         Log.d(TAG, "IllegalArgumentException for " + method.getName());
			    } catch (IllegalAccessException e) {
			         Log.d(TAG, "IllegalAccessException for " + method.getName());
			    } catch (InvocationTargetException e) {
			          Log.d(TAG, "InvocationTargetException for " + method.getName()
			            + "; Reason: " + e.getLocalizedMessage());
			    }
			   return result;
			}

		});
	}
}