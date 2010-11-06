package com.example.brewclock;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
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
  
  /* (non-Javadoc)
   * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.add_tea, menu);
    
    return true;
  }
  
  /* (non-Javadoc)
   * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch(item.getItemId()) {
      case R.id.save_tea:
        if(saveTea()) {
          Toast.makeText(this, getString(R.string.save_tea_success, teaName.getText().toString()), Toast.LENGTH_SHORT).show();
          teaName.setText("");
        }
          
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  /** Methods **/

  /**
   * Read the current interface values, validate them and save the tea to the database.
   * If the values are not valid (e.g. the name is blank), a warning message is displayed.
   * 
   * @return boolean Return true if the tea was saved, false otherwise. 
   */
  public boolean saveTea() {
    // Read values from the interface
    String teaNameText = teaName.getText().toString();
    int brewTimeValue = brewTimeSeekBar.getProgress() + 1;
    
    // Validate a name has been entered for the tea. If not, display
    // an AlertDialog informing the user.
    if(teaNameText.length() < 2) {
      AlertDialog.Builder dialog = new AlertDialog.Builder(this);
      dialog.setTitle(R.string.invalid_tea_title);
      dialog.setMessage(R.string.invalid_tea_no_name);
      dialog.show();
      
      return false;
    }
    
    // The tea is valid, so connect to the tea database and insert the tea
    TeaData teaData = new TeaData(this);
    teaData.insert(teaNameText, brewTimeValue);
    teaData.close();
    
    return true;
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