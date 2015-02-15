package com.nullcognition.retrolambdaexamples.LambdaQuickStart;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nullcognition.retrolambdaexamples.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hugo.weaving.DebugLog;

public class ActivityQuick extends ActionBarActivity {

   @InjectView(R.id.button1)
   Button button1;

   @InjectView(R.id.button2)
   Button button2;

   static class C {

	  @DebugLog // debug log not working with lambdas and method reference, sumbited issue in hugo
	  public static void showTinC(View inView){
		 Button b = (Button)inView;
		 Log.e("in C", "pressed in C");
		 Toast.makeText(inView.getContext(), b.getText(), Toast.LENGTH_SHORT)
			  .show();

	  }
   }

   @DebugLog
   public void showT(View v){
	  Button b = (Button)v;
	  Log.e(getClass().getSimpleName(), "pressed in Activity");
	  Toast.makeText(this, b.getText(), Toast.LENGTH_SHORT)
		   .show();
   }

   @Override
   protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_quick);
	  ButterKnife.inject(this);

	  button1.setOnClickListener((View v) -> {
		 Toast.makeText(this, "test", Toast.LENGTH_SHORT)
			  .show();
	  });

	  button1.setOnClickListener((View v) -> showT(v));
	  button2.setOnClickListener(C::showTinC);

	  runOnUiThread(new Runnable() {

		 @Override
		 public void run(){
			Log.e(getClass().getSimpleName(), "test");
		 }
	  });
	  // vs
	  runOnUiThread(() -> Log.e(getClass().getSimpleName(), "test"));

	  try{
		 if(false){
			throw new NullPointerException();
		 }
		 else{
			throw new InterruptedException();
		 }
	  }
	  catch(NullPointerException | InterruptedException e){
		 // Multiple exceptions work!
		 Log.e("MainActivity", "Exception: ", e);
	  }

	  switch("string"){
		 case "string":
			break;
		 case "not":
			break;
		 default:
			Log.e("Switch 'string'", "Default parameter invalid");
			throw new IllegalArgumentException();
	  }


   }
}
