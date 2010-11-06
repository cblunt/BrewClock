package com.example.brewclock;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class AddTeaActivity 
  extends Activity 
  implements OnSeekBarChangeListener {
  
  /** Properties **/
  protected EditText teaName;
  protected SeekBar brewTimeSeekBar;
  protected TextView brewTimeLabel;

  /** Overrides **/
  
  /* (non-Javadoc)
   * @see android.app.Activity#onCreate(android.os.Bundle)
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_tea);
  
    // Connect interface elements to properties
    teaName = (EditText) findViewById(R.id.tea_name);
    brewTimeSeekBar = (SeekBar) findViewById(R.id.brew_time_seekbar);
    brewTimeLabel = (TextView) findViewById(R.id.brew_time_value);
    
    // Setup Listeners
    brewTimeSeekBar.setOnSeekBarChangeListener(this);
  }

  /** Interface Methods **/
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    if(seekBar == brewTimeSeekBar) {
      // Update the brew time label with the chosen value.
      brewTimeLabel.setText((progress + 1) + " m");
    }
  }

  public void onStartTrackingTouch(SeekBar seekBar) {}

  public void onStopTrackingTouch(SeekBar seekBar) {}
}