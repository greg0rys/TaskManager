package org.shenefelt.Controller.Repository

import org.shenefelt.Controller.Manager.DBConnectionManager
import org.shenefelt.Helpers.SqlFactory
import org.shenefelt.Model.Task
import org.shenefelt.Model.User
import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.collections.Map
import kotlin.collections.ArrayList

class TaskRepo
{

    /**
     * Get all tasks from the database, return them as a map using their priority as a key
     * at each key the value is a list of tasks.
     */
    @Throws(Exception::class)
    fun getTasks(): Map<Int, ArrayList<Task>> {
        val conn = DBConnectionManager.getConnection()

        if (conn.isEmpty) return emptyMap()

        val taskMap = mutableMapOf<Int, ArrayList<Task>>() // all of our tasks.

        conn.get()
            .use { connect ->
            val stmt: PreparedStatement = connect.prepareStatement(SqlFactory().selectAllTasks())
            val rs: ResultSet = stmt.executeQuery()

            while (rs.next())
            {
                val t = Task(
                    rs.getInt("id"),
                    rs.getInt("priority"),
                    rs.getString("title"),
                    rs.getString("desc"),
                    rs.getString("category"),
                    User() // figure out how I want to assign this user.
                )

                taskMap.computeIfAbsent(t.priority) { ArrayList() }.add(t) }
        }

        return taskMap
    }


    @Throws(Exception::class)
    fun addTask(task: Task): Boolean
    {
        val conn = DBConnectionManager.getConnection()
        if (conn.isEmpty) return false

        conn.get()
            .use { conn ->
            val stmt: PreparedStatement = conn.prepareStatement("INSERT INTO Task (TaskId, TaskDescription) VALUES (?, ?)")
            println("Commiting to database..")
            return stmt.executeUpdate() > 0;
        }

    }

    @Throws(Exception::class)
    fun updateTask(task: Task): Boolean
    {
        val conn = DBConnectionManager.getConnection()

        if(conn.isEmpty) return false

        conn.get().use { conn ->
            val stmt: PreparedStatement = conn.prepareStatement("SELECT * FROM M")

            println("Committing to database.. ${ task.title }")
            return stmt.execute()
        }

    }

    @Throws(Exception::class)
    fun getAllUsers(): List<User>
    {
        val conn = DBConnectionManager.getConnection()
        if(conn.isEmpty) return emptyList()

        val users = ArrayList<User>()
        conn.get().use { conn ->
            val stmt: PreparedStatement = conn.prepareStatement(SqlFactory().selectAllUsers())
            val rs: ResultSet = stmt.executeQuery()

            while (rs.next())
                users.add(User())
        }


        return users
    }



}
