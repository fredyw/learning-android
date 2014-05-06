package org.fredy.learningandroid;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.SimpleCursorAdapter;

public class RetrieveFragment extends ListFragment implements LoaderCallbacks<Cursor> {
    private static final int LOADER_ID = 0;
    private SimpleCursorAdapter adapter;
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        adapter = new SimpleCursorAdapter(
            getActivity(),
            R.layout.retrieve_fragment,
            null,
            new String[]{Constants.Column.ID, Constants.Column.MESSAGE},
            new int[]{R.id.retrieve_id_textview, R.id.retrieve_message_textview},
            0);
        setListAdapter(adapter);
        
        getLoaderManager().initLoader(LOADER_ID, null, this);
        
        registerForContextMenu(getListView());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), Constants.URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
        case R.id.retrieve_context_menu_delete:
            getView().getContext().getContentResolver().delete(
                Constants.URI, null, new String[]{Long.toString(info.id)});
            return true;
        case R.id.retrieve_context_menu_update:
            Intent intent = new Intent(getView().getContext(), UpdateActivity.class);
            intent.putExtra(Constants.Column.ID, info.id);
            Cursor cursor = (Cursor) getListAdapter().getItem(info.position);
            String message = cursor.getString(cursor.getColumnIndex(Constants.Column.MESSAGE));
            intent.putExtra(Constants.Column.MESSAGE, message);
            startActivity(intent);
            return true;
        default:
            super.onContextItemSelected(item);
            return true;
        }
    }
}
