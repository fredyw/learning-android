package org.fredy.learningandroid;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment implements OnClickListener {
    private TextView textView;
    private Button button;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        
        button = (Button) view.findViewById(R.id.main_button);
        textView = (TextView) view.findViewById(R.id.main_textview);
        button.setOnClickListener(this);
        
        String message = PreferenceManager.getDefaultSharedPreferences(this.getActivity())
            .getString("message", "");
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this.getActivity(), R.string.message_settings_configure, Toast.LENGTH_LONG).show();
            startActivity(new Intent(this.getActivity(), SettingsActivity.class));
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        String message = PreferenceManager.getDefaultSharedPreferences(this.getActivity())
            .getString("message", getResources().getString(R.string.main_hello_world));
        textView.setText(message);
    }
}
