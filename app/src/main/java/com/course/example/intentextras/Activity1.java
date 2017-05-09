 /*
  * Activity1 starts Activity2 and sends bundle of data.
  * Activity2 sends data bundle back.
  */
package com.course.example.intentextras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity1 extends Activity {
	TextView label;
	TextView labelReturned;
	Button btnCallActivity;
	private final int IPC_ID = 1122;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
			setContentView(R.layout.main);
			label = (TextView) findViewById(R.id.label1);
			labelReturned = (TextView) findViewById(R.id.label1Returned);
			btnCallActivity = (Button) findViewById(R.id.btnCallActivity2);
			
			btnCallActivity.setOnClickListener(new Clicker());
			// for demonstration purposes- show in top label
			label.setText("Activity1   (sending...) \n\n"
					+ "myString:  Hello Android" + "\n"
					+ "myDouble:  3.141592     " + "\n"
					+ "myIntArray: {1, 2, 3, 4} ");
		
	}// onCreate

	private class Clicker implements OnClickListener {
		@Override
		public void onClick(View v) {
		
				// create an Intent to talk to Activity2
				Intent myIntent = new Intent(Activity1.this,
						Activity2.class);

				// prepare a Bundle and add the data to be sent
				Bundle myData = new Bundle();
				myData.putString("myString", "Hello Android");
				myData.putDouble("myDouble", 3.141592);
				myData.putIntArray("myIntArray", new int[] {1, 2, 3, 4});

				// bind the Bundle and the Intent that talks to Activity2
				myIntent.putExtras(myData);

				// call Activity2 and wait for results
				startActivityForResult(myIntent, IPC_ID);
			
		}// onClick
	}// Clicker

	//listener override
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

			switch (requestCode) {
			case IPC_ID: {
				// Activity2 is over - see what happened
				if (resultCode == Activity.RESULT_OK) {

					//get data sent back from Activity2
					Bundle myReturnedData = data.getExtras();
					String myReturnedString1 = myReturnedData
							.getString("myReturnedString");
					Double myReturnedDouble = myReturnedData
							.getDouble("myReturnedDouble");
					String myReturnedString2 = myReturnedData
							.getString("myCurrentTime");
					// display in the bottom label
					labelReturned.setText(myReturnedString1 + "\n"
							+ Double.toString(myReturnedDouble) + "\n"
							+ myReturnedString2);
				} else {
					// user pressed the BACK button
					label.setText("Selection CANCELLED!");
				}// if
				break;
			}// case
			}// switch
		
	}// onActivityResult

}
