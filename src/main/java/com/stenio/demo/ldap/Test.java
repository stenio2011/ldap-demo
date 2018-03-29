package com.stenio.demo.ldap;

/**
 * Created by bjhexin3 on 2018/3/29.
 */
public class Test {
    public static void main(String[] args) {
        LdapAuthentication ldapAuthentication = new LdapAuthentication();
        boolean b = ldapAuthentication.authorize("账号", "密码");
        System.out.println(b);
    }
}
