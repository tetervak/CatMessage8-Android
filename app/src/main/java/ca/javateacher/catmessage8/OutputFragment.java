package ca.javateacher.catmessage8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class OutputFragment extends Fragment {

  public OutputFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_output, container, false);

    // get the arguments
    assert getArguments() != null;
    OutputFragmentArgs args = OutputFragmentArgs.fromBundle(getArguments());
    boolean urgent = args.getUrgent();
    String message = args.getMessage();

    // lookup the views
    TextView urgentView = view.findViewById(R.id.is_urgent_output);
    TextView messageView = view.findViewById(R.id.message_text);

    // display the arguments
    urgentView.setText(urgent ? R.string.urgent : R.string.not_urgent);
    messageView.setText(message);

    // make the back button work
    Button closeButton = view.findViewById(R.id.back_button);
    closeButton.setOnClickListener(this::showInput);

    return view;
  }

  private void showInput(View v) {
    Navigation.findNavController(v).navigate(R.id.action_global_input);
  }

}
