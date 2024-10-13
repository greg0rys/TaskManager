package org.shenefelt.Controller.Manager

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
