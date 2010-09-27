package com.example.brewclock;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BrewClockActivity extends Activity implements OnClickListener {
  /** Properties **/
  protected Button brewAddTime;
  protected Button brewDecreaseTime;
  protected Button startBrew;
  protected TextView brewCountLabel;
  protected TextView brewTimeLabel;

  protected int brewTime = 3;
  protected CountDownTimer brewCountDownTimer;
  protected int brewCount = 0;
  protected boolean isBrewing = false;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // Connect interface elements to properties
    brewAddTime = (Button) findViewById(R.id.brew_time_up);
    brewDecreaseTime = (Button) findViewById(R.id.brew_time_down);
    startBrew = (Button) findViewById(R.id.brew_start);
    brewCountLabel = (TextView) findViewById(R.id.brew_count_label);
    brewTimeLabel = (TextView) findViewById(R.id.brew_time);
    
    // Setup ClickListeners
    brewAddTime.setOnClickListener(this);
    brewDecreaseTime.setOnClickListener(this);
    startBrew.setOnClickListener(this);
    
    // Set the initial brew values
    setBrewCount(0);
    setBrewTime(3);
  }
  
  /** Methods **/
  
  /**
   * Set an absolute value for the number of minutes to brew. Has no effect if a brew
   * is currently running.
   * @param minutes The number of minutes to brew.
   */
  public void setBrewTime(int minutes) {
    if(isBrewing)
      return;
    
    brewTime = minutes;
    
    if(brewTime < 1)
      brewTime = 1;

    brewTimeLabel.setText(String.valueOf(brewTime) + "m");
  }
  
  /**
   * Set the number of brews that have been made, and update the interface. 
   * @param count The new number of brews
   */
  public void setBrewCount(int count) {
    brewCount = count;
    brewCountLabel.setText(String.valueOf(brewCount));
  }
  
  /**
   * Start the brew timer
   */
  public void startBrew() {
    // Create a new CountDownTimer to track the brew time
    brewCountDownTimer = new CountDownTimer(brewTime * 60 * 1000, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        brewTimeLabel.setText(String.valueOf(millisUntilFinished / 1000) + "s");
      }
      
      @Override
      public void onFinish() {
        isBrewing = false;
        setBrewCount(brewCount + 1);
        
        brewTimeLabel.setText("Brew Up!");
        startBrew.setText("Start");
      }
    };
    
    brewCountDownTimer.start();
    startBrew.setText("Stop");
    isBrewing = true;
  }
  
  /**
   * Stop the brew timer
   */
  public void stopBrew() {
    if(brewCountDownTimer != null)
      brewCountDownTimer.cancel();
    
    isBrewing = false;
    startBrew.setText("Start");
  }
  
  /** Interface Implementations **/
  /* (non-Javadoc)
   * @see android.view.View.OnClickListener#onClick(android.view.View)
   */
  public void onClick(View v) {
    if(v == brewAddTime)
      setBrewTime(brewTime + 1);
    else if(v == brewDecreaseTime)
      setBrewTime(brewTime -1);
    else if(v == startBrew) {
      if(isBrewing)
        stopBrew();
      else
        startBrew();
    }
  }
}