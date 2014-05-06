package org.fredy.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        startService(new Intent(this, MyService.class));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.settings_menu:
            startActivity(new Intent(this, SettingsActivity.class));
            break;
        case R.id.create_menu:
            startActivity(new Intent(this, CreateActivity.class));
            break;
        case R.id.retrieve_menu:
            startActivity(new Intent(this, RetrieveActivity.class));
            break;
        }
        return true;
    }
}
