# Task Manager App: Credential Manager
**[Learn about the project here](../index.md) | [📧 Email Me](mailto:greg@gshenefelt.com) | [GitHub](www.github.com/greg0rys) | [Popular JVM Languages](../jvmlangs.md)**

#### Section Resources:
1. [Java Properties Class Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html)
2. [BCrypt Documentation (jBCrypt)](https://www.mindrot.org/projects/jBCrypt/)
3. [Groovy Documentation](https://groovy-lang.org/documentation.html)
---
# Credential Manager - A Groovy Class

### Use Case:
When logging into the program we need a way to verify that you are who you say you are by collecting your username
and password like any typical logon process.\
No matter where you store the users passwords (CSV, Database, ..etc) you **never** store the users password as plain text
instead it should be stored as a hash like so: 
> password { test1234 }  =>  bcrypt hash  { $2b$12$FOxAj6Pvb2euIoLMLouQ9e0pF8QiuGOfNIcWLOV.2Pbv72nk/e/Lq }

I only need to use the plaintext password to hash again and compare that both hashes are equal, 
thus representing a successful logon.

The CredentialManager encapsulates some useful methods to deal with program credentials.

```groovy
import org.mindrot.jbcrypt.BCrypt

def storedhash = "\$2b\$12\$FOxAj6Pvb2euIoLMLouQ9e0pF8QiuGOfNIcWLOV.2Pbv72nk/e/Lq"
def userSuppliedPassword = "test1234"

// verify the password
println BCrypt.checkpw(userSuppliedPassword, storedhash) ? "Passwords Match!" : "No match!"
```
_and this is why we always tell people that we do not know your password! simply because we don't store them as text!_

```groovy
import org.mindrot.jbcrypt.BCrypt

class CredentialManager
{
    private static final Properties PROPS = new Properties()
    private static String url, username, password
    private static boolean loaded = false

    private CredentialManager() { }

    static String hashPass(String pass) {
        BCrypt.hashpw(pass, BCrypt.gensalt(12))
    }

    static boolean checkPass(String pass, String hash) {
        BCrypt.checkpw(pass, hash)
    }


    static List<String> getConnectionLogons() throws Exception {
        if (!loaded)
            loadProps()

        // implicit return 
        List.of(url, username, password)
    }

    /**
     * Load the connection properties
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

    // groovy implicitly returns the last statement so no need to use the 'return' keyword
    static  getUrl()     { if(!loaded) loadProps(); url}
    static getUsername() {  if(!loaded) loadProps(); username }
    static getPassword() {  if(!loaded) loadProps(); password }
}
```

Our program will need this utility class to help keep the credentials as hidden as possible without making things
too overly complex for our example program. However, we will never skimp on password hashing even if it's just an example.

### Where are the return statements?
Groovy will automatically return the last statement in any function implicitly (ruby does the same thing).\
This is something that I take advantage of heavily - why? I'm unsure, I like to be a little specific about the way I write code I suppose.

---
## SPOTTED! Interoperation

```java
import java.util.Properties;
private static final Properties props = new Properties();
```
The Properties class is a native Java class, but since Groovy compiles directly to Java Byte code at runtime 
we are free to throw it right in... with some exceptions of course!

Kotlin and Groovy both support **interoperation** with Java allowing us to
* Easily call Java code directly inside a Kotlin or Groovy file. 
* You can easily use all Java libraries without any extra effort.

Java however **is not designed** to interoperate with other JVM languages meaning we cannot:
* Use Kotlin or Groovy syntax inside a **.java** file or class.
* You cannot use Kotlin or Groovy libraries inside a **.java** file or class.

---


# We can work with credentials
Now it's time to build out our user entities and our database tables. 

Coming next week!

