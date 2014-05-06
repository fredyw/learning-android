package org.fredy.learningandroid;

import android.app.Fragment;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateFragment extends Fragment implements OnClickListener {
    private static final String TAG = CreateFragment.class.getSimpleName();
    
    private EditText editText;
    private Button button;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_fragment, container, false);
        
        editText = (EditText) view.findViewById(R.id.create_edittext);
        button = (Button) view.findViewById(R.id.create_button);
        button.setOnClickListener(this);
        
        return view;
    }

    @Override
    public void onClick(View v) {
        if (R.id.create_button == v.getId()) {
            if (editText.getText().toString().isEmpty()) {
                Toast.makeText(v.getContext(), R.string.create_edittext_empty,
                    Toast.LENGTH_LONG).show();
            } else {
                Log.i(TAG, "Inserting message: " + editText.getText().toString());
                ContentValues values = new ContentValues();
                values.put(Constants.Column.MESSAGE, editText.getText().toString());
                Uri uri = v.getContext().getContentResolver().insert(
                    Constants.URI, values);
                Log.i(TAG, "New row: " + uri.toString());
                Toast.makeText(v.getContext(), R.string.create_edittext_created,
                    Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        }
    }
}
