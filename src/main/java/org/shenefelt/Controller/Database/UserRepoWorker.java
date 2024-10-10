package org.shenefelt.Controller.Database;

import org.shenefelt.Model.User;
import org.shenefelt.Repository.UserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;

public class UserRepoWorker implements UserRepository
{
    private static Connection DBConnectionInstance;
    private int numRecords = 0;
    private final List<User> ALL_USERS = new ArrayList<>();

    public UserRepoWorker()
    {
        DBConnectionInstance = getConnection(); // have it get the connection from inside the factory.
    }

    public UserRepoWorker(Connection DB_CONNECTION)
    {
        DBConnectionInstance = DB_CONNECTION;
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
        return numRecords;
    }

    @Override
    public Optional<User> getUserById(int userID)
    {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers()
    {
        return ALL_USERS; // rather an empty container than an explicit null check later.
    }

    @Override
    public boolean addUser(User U)
    {
        return false;
    }

    @Override
    public boolean updateUser(User U)
    {
        return false;
    }

    @Override
    public Optional<User> getLocalUser(User U)
    {
        return Optional.empty();
    }
}
