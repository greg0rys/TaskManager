# Task Manager App: Credential Manager
**[Learn about the project here](../index.md) | [📧 Email Me](mailto:greg@gshenefelt.com) | [GitHub](www.github.com/greg0rys)**

#### Section Resources:
1. [Java Properties Class Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html)
2. [BCrypt Documentation (jBCrypt)](https://www.mindrot.org/projects/jBCrypt/)
# Credential Manager

### Use Case:
When logging into the program we need a way to verify that you are who you say you are by collecting your username
and password like any typical logon process.\
No matter where you store the users passwords (CSV, Database, ..etc) you **never** store the users password as plain text
instead it should be stored as a hash like so: 
> password { test1234 }  =>  bcrypt hash  { $2b$12$FOxAj6Pvb2euIoLMLouQ9e0pF8QiuGOfNIcWLOV.2Pbv72nk/e/Lq }

I only need to use the plaintext password to hash again and compare that both hashes are equal, 
thus representing a successful logon.

```groovy
import org.mindrot.jbcrypt.BCrypt

def storedhash = "\$2b\$12\$FOxAj6Pvb2euIoLMLouQ9e0pF8QiuGOfNIcWLOV.2Pbv72nk/e/Lq"
def userSuppliedPassword = "test1234"

// verify the password
println BCrypt.checkpw(userSuppliedPassword, storedhash) ? "Passwords Match!" : "No match!"
```
_and this is why we always tell people that we do not know your password! simply because we don't store them as text!_

```groovy
import org.mindrot.jbcrypt.BCrypt // to preform user password hashing and verification
class CredentialManager
{
    private static final Properties PROPS = new Properties() // database credentials stored in a config.properties file
    private static String url, username, password
    private static boolean loaded = false

    private CredentialManager() { } // we are using this as a static class no need for initialization 
    
    // hash the plain text password give 150x
    static String hashPass(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt(12))
    }

    // compare the plaintext password to the stored password hash to verify password
    static boolean checkPass(String pass, String hash) {
        return BCrypt.checkpw(pass, hash)
    }
    
    // return the connection credentials as a list
     static List<String> getConnectionLogons() throws Exception {
        if (!loaded) 
            loadProps()
        
        return [url, username, password]
    }

    /**
     * Load the connection properties from the config file
     * @throws Exception issue reading the config file data.
     */
    private static void loadProps() throws Exception {
        File file = new File('src/main/resources/config.properties')
        InputStream input = new FileInputStream(file)
        try {
            PROPS.load(input)
            url = PROPS.getProperty('db.url')
            username = PROPS.getProperty('db.username')
            password = PROPS.getProperty('db.password')
            loaded = true
        } finally {
            input.close()
        }
    }
}
```

Our program will need this utility class to help keep the credentials as hidden as possible without making things
too overly complex for our example program. However, we will never skimp on password hashing even if it's just an example.

## SPOTTED! Interoperation

```java
import java.util.Properties;
private static final Properties props = new Properties();
```
The Properties class is a native Java class, but since Groovy compiles directly to Java Byte code at runtime 
we are free to throw it right in... with some exceptions of course!

Kotlin and Groovy both support **interoperation** with Java allowing us to:
* Easily call Java code directly inside a Kotlin or Groovy file. 
* You can easily use all Java libraries without any extra effort.

Java however **is not designed** to interoperate with other JVM languages meaning we cannot:
* Use Kotlin or Groovy syntax inside a **.java** file or class.
* You cannot use Kotlin or Groovy libraries inside a **.java** file or class.




