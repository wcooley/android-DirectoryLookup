package cc.nakedape.android.directorylookup;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;

public class LDAPSearchSimple {
    private String attrs[];
    private static String DEFAULT_ATTRS[] = { "cn" };
    private static SearchScope DEFAULT_SCOPE = SearchScope.SUB;
    private static int DEFAULT_PORT = 389;
    private SearchResult result;
 
    
    LDAPSearchSimple(String host, String basedn, String filter)
            throws LDAPException {
        this(host, basedn, filter, DEFAULT_ATTRS, DEFAULT_SCOPE, DEFAULT_PORT);
    }
 
    LDAPSearchSimple(String host, String basedn, String filter, String attrs[])
            throws LDAPException {
        this(host, basedn, filter, attrs, DEFAULT_SCOPE, DEFAULT_PORT);
    }

    public LDAPSearchSimple(String host, String basedn, String filter, String attrs[], SearchScope scope, int port) 
            throws LDAPException {
        
        this.attrs = attrs; 
        LDAPConnection conn = new LDAPConnection(host, port);
        result = conn.search(basedn, scope, filter, attrs);
    }
    
    public SearchResult getResult() {
        return result;
    }

    public String toString() {
        String resultstr = "";
        
        for (SearchResultEntry entry : result.getSearchEntries()) {

            for (String a: attrs) {
                if (!entry.hasAttribute(a)) continue;
                String values = "";
                for (String v: entry.getAttributeValues(a)) {
                    if (values.length() > 0)
                        values = values + ", ";
                    values += v;
                }
                resultstr += a + ": " + values + "\n";
            }
        }

        return resultstr;
    }
}
