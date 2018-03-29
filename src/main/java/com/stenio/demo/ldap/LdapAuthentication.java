package com.stenio.demo.ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by bjhexin3 on 2018/3/29.
 */
public class LdapAuthentication {

    private LocalContext localContext;

    private LdapContext ldapContext;

    public LdapAuthentication(String propertiesLocation) {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(propertiesLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        localContext = new LocalContext(properties);
        init();
    }

    public LdapAuthentication() {
        this("/ldap.properties");
    }

    private void init() {
        System.setProperty("javax.net.ssl.trustStore", localContext.getTrustStore());
        System.setProperty("javax.net.ssl.keyStorePassword", localContext.getKeyStorePassword());
        Hashtable<String, String> env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, localContext.getLdapUrl() + "/" + localContext.getBaseDN());
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, localContext.getSecurityPrincipal() == null ? "" : localContext.getSecurityPrincipal());
        env.put(Context.SECURITY_CREDENTIALS, localContext.getSecurityCredentials() == null ? "" : localContext.getSecurityCredentials());
        try {
            ldapContext = new InitialLdapContext(env, null);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private String getUserDN(String uid) {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String name = "";
        if (localContext.getOu() != null && !"".equals(localContext.getOu().trim())) {
            name = "ou=" + localContext.getOu();
        }
        try {
            NamingEnumeration<SearchResult> results = ldapContext.search(name, "uid=" + uid, searchControls);
            while (results.hasMore()) {
                SearchResult result = results.nextElement();
                return result.getNameInNamespace();
            }
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean authorize(String uid, String password) {
        try {
            String userDN = null;
            if (localContext.getOu() == null || localContext.getOu().trim().equals("")) {
                userDN = getUserDN(uid);
            } else {
                userDN = localContext.getUserIdTag() + "=" + uid + ",ou=" + localContext.getOu() + "," + localContext.getBaseDN();
            }
            if (userDN == null) {
                return false;
            }
            ldapContext.addToEnvironment(Context.SECURITY_PRINCIPAL, userDN);
            ldapContext.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
            ldapContext.reconnect(null);
            return true;
        } catch (NamingException e) {
            return false;
        }
    }
}
