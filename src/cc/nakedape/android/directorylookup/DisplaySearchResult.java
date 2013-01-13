package cc.nakedape.android.directorylookup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplaySearchResult extends Activity {
    private static final String TAG = "DisplaySearchResult";
    String result;
    
    public void setResult(String result) {
        Log.d(TAG, String.format("Setting result '%s'", result));
        this.result = result;
    }
    
    public String getResult() {
        Log.d(TAG, String.format("Getting result '%s'", this.result));
        return this.result;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String attrs[] = {"uid", "displayName", "commonName", "ou", "uidNumber", "telephoneNumber"};    

        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String filter = String.format("(uid=%s)", message);
        
        setContentView(R.layout.activity_display_search_result);
        
        new AsyncLDAPSearch((TextView) findViewById(R.id.display_search_result)).execute(filter);
        
        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
