package org.shenefelt.Controller.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Optional;

public interface Database
{
    default Optional<Connection> getConnection(String URL, String username, String password)
    {
        return Optional.of(DriverManager.getConnection(URL,username,password));
    }

    public int getNumRecords();

    public boolean insertData();
    public Optional<ResultSet> getAllRecords();


}
