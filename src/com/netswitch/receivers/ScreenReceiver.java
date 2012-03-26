package com.netswitch.receivers;



import com.netswitch.Values;
import com.netswitch.models.Screen;
import com.netswitch.utils.DeviceUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver {
     
    // thanks Jason
    public static boolean wasScreenOn = true;
    Values session;
    
    
    @Override
    public void onReceive(Context context, Intent intent) {
    	session = (Values) context.getApplicationContext();
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
        	
        	session.AddScreen(false);
            wasScreenOn = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
        	
        	session.AddScreen(true);
        	wasScreenOn = true;

        }
    }

}
