package com.netswitch;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NetSwitchActivity extends Activity {
    /** Called when the activity is first created. */
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
                
                //ConnectivityManager connectivityManager =(ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE); 
             	//NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
             	

            }    
        });
    }
}