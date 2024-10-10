package org.shenefelt.Controller.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public interface Database
{
    static Optional<Connection> getConnection(String URL, String username, String password) throws SQLException
    {

        return Optional.of(DriverManager.getConnection(URL,username,password));
    }

    public int getNumRecords();

    public boolean insertData();
    public Map<Integer,Database> getAllRecords() throws SQLException;


}
