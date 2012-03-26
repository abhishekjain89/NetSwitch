package com.netswitch.ui.viewgenerator;
import com.netswitch.models.Model;
import com.netswitch.models.Row;
import com.netswitch.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class KeyProgressViewGenerator extends ViewGenerator{

	ViewHolder holder;

	public KeyProgressViewGenerator(int resource) {
		super(resource);
		holder = new ViewHolder();
	}

	@Override
	public ViewHolder fillViewHolder(View view, LayoutInflater inflater) {
		
		
		holder.first =  (TextView) view.findViewById(R.id.key);
		holder.second =  (TextView) view.findViewById(R.id.message);
		holder.progress =  (ProgressBar) view.findViewById(R.id.value);
		
		return holder;
	}
	@Override
	public void populateView(Row item) {
		holder.first.setText(item.first);
		holder.progress.setProgress(item.value);
		holder.second.setText(item.second);
	}
	

}
