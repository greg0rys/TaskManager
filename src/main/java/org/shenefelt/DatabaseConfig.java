package org.shenefelt;

import org.shenefelt.Controller.Database.Database;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig
{
    private static final Properties PROPS = new Properties();
    private static String url,username,password;
    private DatabaseConfig() { } // cannot initalize

    public static void load() throws Exception { loadProps(); }
    private static void loadProps() throws Exception
    {
        try(InputStream input = new FileInputStream("src/main/resources/cofig.properties"))
        {
            PROPS.load(input);

            url = PROPS.getProperty("db.url");
            username = PROPS.getProperty("db.username");
            password = PROPS.getProperty("db.password");

        }
    }

    public static String getUrl() { return url; }
    public static String getUsername () { return username; }
    public static String getPassword() { return password; }
}
