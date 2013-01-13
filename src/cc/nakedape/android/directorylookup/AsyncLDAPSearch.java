package cc.nakedape.android.directorylookup;

import com.unboundid.ldap.sdk.LDAPException;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class AsyncLDAPSearch extends AsyncTask<String, Void, String> {

    private static final String TAG = "AsyncLDAPSearch";
    TextView textView;
    
    String attrs[] = {"uid", "displayName", "commonName", "ou", "uidNumber", "telephoneNumber"};    

    public AsyncLDAPSearch(TextView textView) {
        this.textView = textView;
    }
    
    @Override
    protected String doInBackground(String... args) {
        String filter = args[0];
        
        String result;
        try {
            result = (new LDAPSearchSimple("ldap.oit.pdx.edu", "dc=pdx,dc=edu", filter, attrs)).toString();
        }
        catch (LDAPException e) {
            result = e.toString();
        }
        return result;
    }
    
    @Override
    protected void onPostExecute(String result) {        
        textView.setText(result);
    }

}
