package com.example.brewclock;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class BrewClockActivity extends Activity {
  /** Properties **/
  protected Button brewAddTime;
  protected Button brewDecreaseTime;
  protected Button startBrew;
  protected TextView brewCountLabel;
  protected TextView brewTimeLabel;

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
  }
}