package com.netswitch.activities;

import com.netswitch.R;
import com.netswitch.models.Measurement;
import com.netswitch.utils.RatingUtil;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class PerformanceRating extends Dialog {

	Measurement measure;
	
	public PerformanceRating(Context context,Measurement measure) {
		super(context);
		
		this.measure = measure;
		
		
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.rating);

		super.setCancelable(true);

		
		Button close_button = (Button) super.findViewById(R.id.close);
		TextView rating = (TextView) super.findViewById(R.id.rating);
		TextView suggestion = (TextView) super.findViewById(R.id.suggestion);
		
		
		RatingUtil util  = new RatingUtil(measure);
		
		int ratingScore = util.getRating();
		
		
		close_button.setOnClickListener(new ImageView.OnClickListener() {      
			public void onClick(View view) { 
				dismiss();    
			} 
		});
		
		rating.setText("Rating: " + ratingScore + " points");
		suggestion.setText("Suggest switch to 3G");
		
	}

}
