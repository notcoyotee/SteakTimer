package com.GbC.SteakTimer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


public class SteakTimerActivity extends Activity {
public int SteakType=0;
public int SteakSize=0;
public int CookTime=2;
public int CurrentSide=1;

//public ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		final Button StrtButton = (Button) findViewById(R.id.StartTimerButton);
		StrtButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (SteakType==0){ //Rare 120°- 130°F
					if (SteakSize==0) {
						CookTime = (CurrentSide==1) ? 2 : 2;
					}
					else if(SteakSize==1){
						CookTime = (CurrentSide==1) ? 4 : 2;
					}
					else if(SteakSize==2){
						CookTime = (CurrentSide==1) ? 5 : 3;
					}
					else if(SteakSize==3){
						CookTime = (CurrentSide==1) ? 5 : 4;
					}
					else if(SteakSize==4){
						CookTime = (CurrentSide==1) ? 6 : 4;
					}
					else if(SteakSize==5){
						CookTime = (CurrentSide==1) ? 7 : 5;
					}
					else if(SteakSize==6){
						CookTime = (CurrentSide==1) ? 8 : 6;
					}
				}
				else if (SteakType==1){ //Medium Rare 130°-140°F
					if (SteakSize==0) {
						CookTime = (CurrentSide==1) ? 3 : 2;
					}
					else if(SteakSize==1){
						CookTime = (CurrentSide==1) ? 4 : 3;
					}
					else if(SteakSize==2){
						CookTime = (CurrentSide==1) ? 5 : 4;
					}
					else if(SteakSize==3){
						CookTime = (CurrentSide==1) ? 6 : 5;
					}
					else if(SteakSize==4){
						CookTime = (CurrentSide==1) ? 7 : 5;
					}
					else if(SteakSize==5){
						CookTime = (CurrentSide==1) ? 8 : 6;
					}
					else if(SteakSize==6){
						CookTime = (CurrentSide==1) ? 9 : 8;
					}
				}
				else if (SteakType==2){ //Medium 140°-150°F
					if (SteakSize==0) {
						CookTime = (CurrentSide==1) ? 4 : 2;
					}
					else if(SteakSize==1){
						CookTime = (CurrentSide==1) ? 5 : 3;
					}
					else if(SteakSize==2){
						CookTime = (CurrentSide==1) ? 65 : 4;
					}
					else if(SteakSize==3){
						CookTime = (CurrentSide==1) ? 6 : 5;
					}
					else if(SteakSize==4){
						CookTime = (CurrentSide==1) ? 7 : 5;
					}
					else if(SteakSize==5){
						CookTime = (CurrentSide==1) ? 8 : 6;
					}
					else if(SteakSize==6){
						CookTime = (CurrentSide==1) ? 9 : 8;
					}
				}
				else if (SteakType==3){ //Well Done 160°-170°F
					if (SteakSize==0) {
						CookTime = (CurrentSide==1) ? 5 : 3;
					}
					else if(SteakSize==1){
						CookTime = (CurrentSide==1) ? 7 : 5;
					}
					else if(SteakSize==2){
						CookTime = (CurrentSide==1) ? 8 : 6;
					}
					else if(SteakSize==3){
						CookTime = (CurrentSide==1) ? 9 : 7;
					}
					else if(SteakSize==4){
						CookTime = (CurrentSide==1) ? 10 : 8;
					}
					else if(SteakSize==5){
						CookTime = (CurrentSide==1) ? 11 : 9;
					}
					else if(SteakSize==6){
						CookTime = (CurrentSide==1) ? 13 : 11;
					}					
				}
				
				ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
				bar.setMax(CookTime*60000);
				//bar.setProgress(100);

				final MyCount counter = new MyCount((CookTime*60000), 1000);
				//final MyCount counter = new MyCount((3000), 1000);
				//Toast.makeText(getApplicationContext(),"CurrentSide = " + CurrentSide + "; Cooktime = " + CookTime+" minutes",Toast.LENGTH_LONG).show();

				StrtButton.setEnabled(false);
				Spinner spnr1 = (Spinner) findViewById(R.id.spinner1);
				spnr1.setEnabled(false);
				Spinner spnr2 = (Spinner) findViewById(R.id.spinner2);
				spnr2.setEnabled(false);
				MediaPlayer TickAudioPlayer= MediaPlayer.create(getApplicationContext(),R.raw.clockticking);

				TickAudioPlayer.setLooping(true);
				TickAudioPlayer.start();
				counter.start();
			}
		});

		Spinner spnrType;
		spnrType = (Spinner) findViewById(R.id.spinner1);
		spnrType
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> adapterView,
							View view, int i, long l) { 
						SteakType=i;
						final Button StrtButton = (Button) findViewById(R.id.StartTimerButton);
						StrtButton.setEnabled(true);
					}
					public void onNothingSelected(AdapterView<?> adapterView) {
						return;
					}
				});
		
		Spinner spnrSize;
		spnrSize = (Spinner) findViewById(R.id.spinner2);
		spnrSize
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> adapterView,
							View view, int i, long l) { 
								SteakSize=i;
						final Button StrtButton = (Button) findViewById(R.id.StartTimerButton);
						StrtButton.setEnabled(true);
					}
					public void onNothingSelected(AdapterView<?> adapterView) {
						return;
					}
				});
	}

	public class MyCount extends CountDownTimer {
		ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
		TextView mTextView = (TextView) findViewById(R.id.textView4);
		 Button StrtButton = (Button) findViewById(R.id.StartTimerButton);
			MediaPlayer DoneAudioPlayer= MediaPlayer.create(getApplicationContext(),R.raw.timerdone);

			


		//ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);

		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			//final TextView mTextView = (TextView) findViewById(R.id.textView4);
			//TickAudioPlayer.stop();
			DoneAudioPlayer.start();
			CurrentSide=CurrentSide+1;
			if (CurrentSide==2){
				mTextView.setText("Turn the Steak over and click Start");
				StrtButton.setEnabled(true);
			}
			else{
				mTextView.setText("Allow 3-5 minutes resting time before serving. \n Apply finishing sauce or glaze if desired.");
			}
			}

		@Override
		public void onTick(long millisUntilFinished) {
			mTextView.setText("Time Remaining: " + formatTime(millisUntilFinished));
			//ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);

			bar.setProgress((int)millisUntilFinished);

		}
	     public String formatTime(long millis) {
             String output = "";
             long seconds = millis / 1000;
             long minutes = seconds / 60;
             long hours = minutes / 60;
             seconds = seconds % 60;
             minutes = minutes % 60;
             hours = hours % 24;

             String secondsD = String.valueOf(seconds);
             String minutesD = String.valueOf(minutes);

             if (seconds < 10)
               secondsD = "0" + seconds;
             if (minutes < 10)
               minutesD = "0" + minutes;
             if (hours < 10){
               //hoursD = "0" + hours;
             }

             output += minutesD + ":" + secondsD;
             return output;
   }

	}
}