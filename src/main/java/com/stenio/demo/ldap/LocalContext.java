package com.stenio.demo.ldap;

import java.util.Properties;

/**
 * Created by bjhexin3 on 2018/3/29.
 */
public class LocalContext {

    private String trustStore;

    private String keyStorePassword;

    private String ldapUrl;

    private String baseDN;

    private String securityPrincipal;

    private String securityCredentials;

    private String ou;

    private String userIdTag;

    public LocalContext() {

    }

    public LocalContext(Properties properties) {
        this.trustStore = properties.getProperty("trust_store");
        this.keyStorePassword = properties.getProperty("key_store_password");
        this.ldapUrl = properties.getProperty("ldap_url");
        this.baseDN = properties.getProperty("base_dn");
        this.securityPrincipal = properties.getProperty("security_principal");
        this.securityCredentials = properties.getProperty("security_credentials");
        this.userIdTag = properties.getProperty("user_id_tag");
        this.ou = properties.getProperty("ou");
    }

    public String getTrustStore() {
        return trustStore;
    }

    public void setTrustStore(String trustStore) {
        this.trustStore = trustStore;
    }

    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    public String getSecurityPrincipal() {
        return securityPrincipal;
    }

    public void setSecurityPrincipal(String securityPrincipal) {
        this.securityPrincipal = securityPrincipal;
    }

    public String getSecurityCredentials() {
        return securityCredentials;
    }

    public void setSecurityCredentials(String securityCredentials) {
        this.securityCredentials = securityCredentials;
    }

    public String getLdapUrl() {
        return ldapUrl;
    }

    public void setLdapUrl(String ldapUrl) {
        this.ldapUrl = ldapUrl;
    }

    public String getBaseDN() {
        return baseDN;
    }

    public void setBaseDN(String baseDN) {
        this.baseDN = baseDN;
    }

    public String getUserIdTag() {
        return userIdTag;
    }

    public void setUserIdTag(String userIdTag) {
        this.userIdTag = userIdTag;
    }

    public String getOu() {
        return ou;
    }

    public void setOu(String ou) {
        this.ou = ou;
    }

    @Override
    public String toString() {
        return "LocalContext{" +
                "trustStore='" + trustStore + '\'' +
                ", keyStorePassword='" + keyStorePassword + '\'' +
                ", ldapUrl='" + ldapUrl + '\'' +
                ", baseDN='" + baseDN + '\'' +
                ", securityPrincipal='" + securityPrincipal + '\'' +
                ", securityCredentials='" + securityCredentials + '\'' +
                ", ou='" + ou + '\'' +
                ", userIdTag='" + userIdTag + '\'' +
                '}';
    }
}
