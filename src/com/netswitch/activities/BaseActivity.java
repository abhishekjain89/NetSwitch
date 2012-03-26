package com.netswitch.activities;

import com.netswitch.helpers.MenuHelper;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuHelper.fillMenu(menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		Log.v("menu","onOptionsItemSelected "+item.getItemId());
		MenuHelper.onMenuItemSelected(item, this);
		return true;

	}
	
}
