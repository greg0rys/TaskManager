package org.shenefelt.Controller.Repository

import org.shenefelt.Controller.Manager.CredentialManager
import org.shenefelt.Controller.Manager.DBConnectionManager
import org.shenefelt.Helpers.SqlFactory
import org.shenefelt.Model.User
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

// User repository used to manage user actions in the database
class UserRepo {

    private SqlFactory sqlFactory = new SqlFactory()

    void getAllUsers(List<User> userList) throws Exception {
        Optional<Connection> connection = DBConnectionManager.getConnection()

        if (!connection.isPresent()) return // No connection to DB

        Connection conn = null
        PreparedStatement ps = null
        ResultSet rs = null

        try {
            conn = connection.get()
            ps = conn.prepareStatement(sqlFactory.selectAllUsers())
            rs = ps.executeQuery()

            while (rs.next()) {
                userList.add(new User())
            }
        } finally {
            // Close resources in reverse order of their opening
            if (rs != null) rs.close()
            if (ps != null) ps.close()
            if (conn != null) conn.close()
        }
    }

    ArrayList<User> getAllUsersAuthInfo() throws Exception {
        Optional<Connection> connection = DBConnectionManager.getConnection()

        if (!connection.isPresent()) return new ArrayList<>()

        ArrayList<User> userInfo = new ArrayList<>()

        Connection conn = null
        PreparedStatement ps = null
        ResultSet rs = null

        try {
            conn = connection.get()
            ps = conn.prepareStatement(sqlFactory.selectUserAuths())
            rs = ps.executeQuery()

            while (rs.next()) {
                userInfo.add(new User(rs.getString("username"), rs.getString("password")))
            }
        } finally {
            // Close resources in reverse order of their opening
            if (rs != null) rs.close()
            if (ps != null) ps.close()
            if (conn != null) conn.close()
        }

        return userInfo
    }



    /**
     * @param user the user we are adding
     * @return true if the user was added. False if they weren't.
     */
    boolean addUser(User user) throws Exception {
        Optional<Connection> connection = DBConnectionManager.getConnection()

        if (!connection.isPresent()) return false

        Connection conn = null
        PreparedStatement ps = null

        try {
            conn = connection.get()
            ps = conn.prepareStatement(sqlFactory.insertNewUser())
            ps.setInt(1, user.id)
            ps.setString(2, user.username)
            ps.setString(3, CredentialManager.hashPass(user.password))

            return ps.executeUpdate() > 0
        } finally {
            // Close resources
            if (ps != null) ps.close()
            if (conn != null) conn.close()
        }
    }

    /**
     * @param user the user that we want to update.
     * @return true if the user was updated, false if they were not.
     */
    boolean updateUser(User user) {
        // Add implementation when required
        return false
    }
}
