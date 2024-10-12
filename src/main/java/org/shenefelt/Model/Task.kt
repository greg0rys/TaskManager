package org.shenefelt.Model;

class Task(
    var taskID: Int,
    var priority: Int,
    var title: String,
    var description: String,
    var category: String,
    var assignedUser: User
) {
    init {
        title = ""
        description = ""
        category = ""
        assignedUser = User()
        priority = 2
    }
    // no need for explicit get / set methods they are implicit and java files can still call them getName() seName() etc

    override fun toString(): String {
        return "Task { ID= $taskID Title= $title, description = $description, assignedUser = $assignedUser, priority = $priority, " +
                "category = $category }"
    }

}
