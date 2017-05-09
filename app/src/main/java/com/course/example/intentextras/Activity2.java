/* Activity2. This activity receives a bundle of data,
// performs some work on the data and, returns results
 * to Activity1.
 */
package com.course.example.intentextras;


import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity2 extends Activity {
    TextView label;
    Button   btnCallActivity;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        
        label = (TextView)findViewById(R.id.label2);
        btnCallActivity = (Button)findViewById(R.id.btnCallActivity1);
        btnCallActivity.setOnClickListener(new Clicker());
        
        //create a local Intent object; we have been called!
        Intent myLocalIntent = getIntent();

        //grab the data package with all the pieces sent to us
        Bundle myBundle = myLocalIntent.getExtras();

        //extract the individual data parts of the bundle 
        String str = myBundle.getString("myString");
        double dob = myBundle.getDouble("myDouble");
        int[]  arr = myBundle.getIntArray("myIntArray");
        
        //do something with the data here (for example...)
        String strArr  = "{ ";
        int sumIntValues = 0;
        for (int i=0; i<arr.length; i++) {
        	sumIntValues = sumIntValues + arr[i];
        	strArr = strArr + Integer.toString( arr[i] ) + " ";
        }
        strArr = strArr + " }";

        //show arriving data on screen
        label.setText("Activity2   (receiving...) \n\n" +
        		       "myString:   " + str + "\n" + 
        		       "myDouble:   " + Double.toString(dob) + "\n" + 
        		       "myIntArray: " + strArr);
                
        //now go back to myActivity1 with some data made here    
        double someNumber = sumIntValues + dob;
        myBundle.putString("myReturnedString", "Android Rocks!!!");
        myBundle.putDouble("myReturnedDouble", someNumber);
        myBundle.putString("myCurrentTime", new Date().toString() );
        myLocalIntent.putExtras(myBundle);

        setResult(Activity.RESULT_OK, myLocalIntent);
        
    }//onCreate
    
    private class Clicker implements OnClickListener {
		@Override
		public void onClick(View v) {
			//clear Activity2 screen so Activity1 could be seen
			finish();			
		}  	
    }

}
