package org.shenefelt.Controller.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class UserDB implements Database
{
    private static Connection DBConnectionInstance;
    private int numRecords = 0;

    public UserDB()
    {
        DBConnectionInstance = getConnection(); // have it get the connection from inside the factory.
    }

    @Override
    public boolean insertData()
    {
        try (Statement statement = DBConnectionInstance.createStatement()) {
            String query = "INSERT INTO TableName (column1, column2) VALUES (value1, value2)";
            int rowsAffected = statement.executeUpdate(query);
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<ResultSet> getAllRecords()
    {
        return Optional.empty();
    }

    @Override
    public int getNumRecords()
    {

    }
}
