package org.shenefelt.Repository;

import org.shenefelt.Model.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserRepository
{
    // get a specific user by ID
    public Optional<User> getUserById(int userID);
    // get a list of all users
    public List<User> getAllUsers();
    // add user
    boolean addUser(User U);
    boolean updateUser(User U);
    Optional<User> getLocalUser(User U);
    private boolean setup()
    {
        return true;
    }

}
