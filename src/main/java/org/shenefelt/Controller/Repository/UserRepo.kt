package org.shenefelt.Controller.Repository

import org.shenefelt.Controller.Manager.CredentialManager
import org.shenefelt.Controller.Manager.DBConnectionManager
import org.shenefelt.Helpers.SqlFactory
import org.shenefelt.Model.User
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.Optional

// User repository used to manage user actions in the database
class UserRepo {

    private val sqlFactory = SqlFactory()

    @Throws(Exception::class)
    fun getAllUsers(userList: MutableList<User>) {
        val connection = DBConnectionManager.getConnection()

        if (connection.isEmpty) return // No connection to DB

        connection.get().use { conn ->
            val ps: PreparedStatement = conn.prepareStatement(sqlFactory.selectAllUsers())
            val rs: ResultSet = ps.executeQuery()

            while (rs.next())
                userList.add(User())

        }
    }

    @Throws(Exception::class)
    fun getAllUsersAuthInfo(): List<User>
    {
        val connection: Optional<Connection> = DBConnectionManager.getConnection()

        if (connection.isEmpty) return emptyList()

        val userInfo = ArrayList<User>()

        connection.get().use { conn ->
            val ps: PreparedStatement = conn.prepareStatement(sqlFactory.selectUserAuths())
            val rs: ResultSet = ps.executeQuery()

            while (rs.next())
                userInfo.add(User(rs.getString("username"),
                                 rs.getString("password")))
        }

        return userInfo
    }

    /**
     * @param user the user we are adding
     * @return true if the user was added. False if they weren't.
     */
    @Throws(Exception::class)
    fun addUser(user: User): Boolean {
        val connection: Optional<Connection> = DBConnectionManager.getConnection()

        if (connection.isEmpty) return false

        connection.get().use { conn ->
            val ps: PreparedStatement = conn.prepareStatement(sqlFactory.insertNewUser())
            ps.setInt(1, user.id)
            ps.setString(2, user.username)
            ps.setString(3, CredentialManager.hashPass(user.password))

            return ps.executeUpdate() > 0
        }
    }

    /**
     * @param user the user that we want to update.
     * @return true if the user was updated, false if they were not.
     */
    fun updateUser(user: User): Boolean {
        // Add implementation when required
        return false
    }
}
