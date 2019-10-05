package ca.javateacher.catmessage8;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import ca.javateacher.catmessage8.InputFragmentDirections.ActionInputToOutput;

public class InputFragment extends Fragment {

  private CheckBox mUrgentCheckBox;
  private RadioGroup mMessageGroup;

  private SharedPreferences mPreferences;

  public InputFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_input, container, false);

    // lookup the views
    mUrgentCheckBox = view.findViewById(R.id.urgent_check_box);
    mMessageGroup = view.findViewById(R.id.message_group);

    Button sendButton = view.findViewById(R.id.send_button);
    sendButton.setOnClickListener(this::showOutput);

    // lookup the preferences object
    mPreferences =
            PreferenceManager.getDefaultSharedPreferences(getContext());

    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
    boolean urgent = mPreferences.getBoolean(getString(R.string.urgent_key), false);
    mUrgentCheckBox.setChecked(urgent);
    String message
            = mPreferences.getString(getString(R.string.message_text_key),
            getString(R.string.default_message_value));
    assert message != null;
    switch(message){
      case "purr":{
        mMessageGroup.check(R.id.purr_button);
        break;
      }
      case "mew":{
        mMessageGroup.check(R.id.mew_button);
        break;
      }
      case "hiss":{
        mMessageGroup.check(R.id.hiss_button);
        break;
      }
    }
  }

  private void showOutput(View v) {
    // get urgent flag value
    boolean urgent = mUrgentCheckBox.isChecked();
    // get the selected message text
    String message;
    switch (mMessageGroup.getCheckedRadioButtonId()) {
      case R.id.purr_button:
        message = getString(R.string.cat_purr);
        break;
      case R.id.mew_button:
        message = getString(R.string.cat_mew);
        break;
      case R.id.hiss_button:
        message = getString(R.string.cat_hiss);
        break;
      default:
        message = getString(R.string.undefined);
    }

    ActionInputToOutput action
        = InputFragmentDirections.actionInputToOutput();
    action.setUrgent(urgent);
    action.setMessage(message);
    Navigation.findNavController(v).navigate(action);
  }

}
