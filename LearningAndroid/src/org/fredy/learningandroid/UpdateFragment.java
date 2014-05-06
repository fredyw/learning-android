package org.fredy.learningandroid;

import android.app.Fragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFragment extends Fragment implements OnClickListener {
    private EditText updateEditText;
    private Button updateButton;
    private long currentId;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_fragment, container, false);
        updateEditText = (EditText) view.findViewById(R.id.update_edittext);
        currentId = getActivity().getIntent().getLongExtra(Constants.Column.ID, 0L);
        String message = getActivity().getIntent().getStringExtra(Constants.Column.MESSAGE);
        updateEditText.setText(message);
        updateButton = (Button) view.findViewById(R.id.update_button);
        updateButton.setOnClickListener(this);
        
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.update_button:
            if (updateEditText.getText().toString().isEmpty()) {
                Toast.makeText(v.getContext(), R.string.update_edittext_empty,
                    Toast.LENGTH_LONG).show();
            } else {
                ContentValues values = new ContentValues();
                values.put(Constants.Column.MESSAGE, updateEditText.getText().toString());
                getActivity().getContentResolver().update(
                    Constants.URI, values, null, new String[]{Long.toString(currentId)});
                Toast.makeText(getActivity(), R.string.update_textview_updated,
                    Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
            break;
        }
    }
}
