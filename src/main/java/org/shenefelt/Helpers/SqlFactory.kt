package org.shenefelt.Helpers

import org.shenefelt.Model.Interfaces.SQLQuery
import org.shenefelt.Model.User

class SqlFactory: SQLQuery
{
    override fun selectAllTasks(): String
    {
        return "SELECT * FROM Tasks"
    }

    override fun selectAllUsers(): String
    {
        return "SELECT * FROM Users"
    }

    override fun selectUserById(userId: Int): String
    {
        return "SELECT * FROM Users WHERE id = $userId"
    }

    override fun selectUserAuths(): String
    {
        return "SELECT username, password FROM Users"
    }

    override fun insertNewUser(): String
    {
        return "INSERT INTO User VALUES(?,?,?)"
    }
}