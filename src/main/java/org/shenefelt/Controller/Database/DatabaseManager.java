package org.shenefelt.Controller.Database;

import org.shenefelt.DatabaseConfig;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DatabaseManager implements Database
{
    private final static String URL = DatabaseConfig.getUrl();
    private final static String USERNAME = DatabaseConfig.getUsername();
    private final static String PASSWORD = DatabaseConfig.getPassword();
    private final List<Map<Integer, Database>> TABLE_DATA = new ArrayList<>();
    private String tableName =" ";


    private DatabaseManager() throws SQLException { /* no init */ }

    public boolean load()
    {
        if
    }
    public static class Builder()
    {
        public static boolean insertIntoTableByID(int id) throws Exception
        {
            Optional<Connection> conn = Database.getConnection(URL,USERNAME,PASSWORD);
            if(conn.isEmpty()) return false;

            switch(id)
            {
                default: break;
            }

            return false;
        }
    }

    @Override
    public int getNumRecords()
    {

    }

    @Override
    public boolean insertData()
    {
        return false;
    }

    @Override
    public Map<Integer, Database> getAllRecords()
    {
        return new HashMap<>();
    }
}
