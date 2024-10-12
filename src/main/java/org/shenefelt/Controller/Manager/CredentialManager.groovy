package org.shenefelt.Controller.Manager

import org.mindrot.jbcrypt.BCrypt

class CredentialManager
{
    private static final Properties PROPS = new Properties()
    private static String url, username, password
    private static boolean loaded = false

    private CredentialManager() { }

    static String hashPass(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt(150))
    }

    static boolean checkPass(String pass, String hash) {
        return BCrypt.checkpw(pass, hash)
    }


     static List<String> getConnectionLogons() throws Exception {
        if (!loaded) {
            loadProps()
        }
        return List.of(url, username, password)
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
}
