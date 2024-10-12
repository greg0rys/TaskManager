package org.shenefelt.Controller.Manager;

import org.shenefelt.Controller.Repository.TaskRepo
import org.shenefelt.Model.Task
import kotlin.collections.ArrayList

class TaskManager {
    val taskList =
        HashMap<Int, ArrayList<Task>>() // hash priority is the key tasks of that priority are stored in that list
    val taskRepo = TaskRepo()
    var numberOfTasks = 0

    init
    {
        /* load all the tasks from the database */
        taskList.putAll( taskRepo.getTasks() )
        // shorter hand of the get count method below
        numberOfTasks = taskList.values.sumOf { it.size }
    }

    /* add a task here to the map */
    fun addTask(task: Task): Boolean
    {
        var taskSet = taskList.get(task.priority)
        taskSet?.add(task) // null safety & always add to local before DB to keep them in sync without extra calls

        numberOfTasks++
        return taskRepo.addTask(task) // put the task in the database

    }

    /* get a list of tasks that have the same priority */
    fun getTasksByPriority(priority: Int): ArrayList<Task>
    {
        return taskList[priority] ?: ArrayList() // either the task or an empty arr list
    }

    /* find the task with a matching name or null.*/
    fun getTaskByName(name: String): Task? {
        // _ ingores the key of the map since I am looking for a string.
        taskList.forEach { (_, tasks) ->

            tasks.forEach { task ->

                if (task.title == name) return task
            }
        }
        return null  // no task here..
    }

    /* get total tasks from all lists. */
    fun getNumberOfTasks(): Int {
        var numberOfTasks = 0

        // go through each keys values and count
        for( task in taskList)
            numberOfTasks += task.value.size

        return numberOfTasks
    }

}


