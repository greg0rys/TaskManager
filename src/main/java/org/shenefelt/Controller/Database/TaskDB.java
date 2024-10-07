package org.shenefelt.Controller.Database;

import org.shenefelt.Model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class TaskDB implements Database
{
    // store all the records organized by priority e.g. if priority is 4 then we have a hashed key 4
    // all tasks that match that priority will be added to the listed stored at the location.
    //slay
    private final Map<Integer, List<Task>> RECORDS = new HashMap<>();

    public boolean addTask(Task task) throws SQLException
    {

        if (RECORDS.containsKey(task.getPriority()))
        {
            RECORDS.get(task.getPriority()).add(task);
        }
        else
        {
            List<Task> tasks = new ArrayList<>();
            tasks.add(task);
            RECORDS.put(task.getPriority(), tasks);
        }

        return commitNewTask(task);
    }

    private boolean commitNewTask(Task T) throws SQLException
    {
        if(T == null) return false;

        try(Connection conn = getConnection())
        {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM TEMP");
        }

        return true;
    }

    /**
     * Optional values allow us to easily protect ourselves from NULL values.
     * @param priority the priority of the task e.g. key.
     * @param name the name of the task
     * @return The task if found else an empty optional.
     */
    public Optional<Task> getTask(int priority, String name)
    {
        Optional<Task> target = Optional.empty();

        for (List<Task> tasks : RECORDS.values())
        {
            for (Task t : tasks)
            {
                if ((t.getPriority() == priority) && (t.getName().equals(name)))
                {
                    target = Optional.of(t);
                    return target;
                }
            }
        }

        return target;
    }

    // no need to use optionals here the RECORDS field is a final value so it's always as init ob this object.
    public Map<Integer, List<Task>> getAllTasks()
    {
        return RECORDS;
    }
    @Override
    public Optional<Connection> getConnection()
    {
        Optional<Connection> conn = Optional.empty();

    }

    @Override
    public int getNumRecords() { return RECORDS.size(); }

}
