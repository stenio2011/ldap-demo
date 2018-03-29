ldap demo

### import a certification into your keystore

First, convert your certificate in a DER format : 

` copenssl x509 -outform der -in certificate.pem -out certificate.der `

And after, import it in the keystore :

` keytool -import -alias your-alias -keystore cacerts -file certificate.der`

### In Tomcat

If you use tomcat as web application container, the default key store is in your `$JRE_HOME/lib/security/cacerts` file. and the password will be `changeit`

