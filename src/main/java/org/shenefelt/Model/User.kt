package org.shenefelt.Model

class User {
    var id: Int = 0
    var numberOfTasks: Int = 0
    var numberOfCompletedTasks: Int = 0
    var numberOfOverdueTasks: Int = 0
    var fName: String? = ""
    var lName: String? = ""
    var password: String? = ""
    var username: String? = ""
    var isNull: Boolean = true
    var isAdmin: Boolean = false
    var tasks: Map<Int, Task> = HashMap() // tasks sorted by priority

    // Default constructor
    constructor() {
        isNull = true
        isAdmin = false
    }


    // Copy constructor
    constructor(user: User) {
        id = user.id
        numberOfTasks = user.numberOfTasks
        numberOfCompletedTasks = user.numberOfCompletedTasks
        numberOfOverdueTasks = user.numberOfOverdueTasks
        fName = user.fName
        lName = user.lName
        password = user.password
        username = user.username
        isNull = user.isNull
        isAdmin = user.isAdmin
        tasks = HashMap(user.tasks)
    }

    // Constructor with all fields
    constructor(
        dbID: Int, numTasks: Int, numCompletedTasks: Int,
        numOverdueTasks: Int, first: String?, last: String?,
        uName: String?, pass: String?, noValue: Boolean
    ) {
        id = dbID
        numberOfTasks = numTasks
        numberOfCompletedTasks = numCompletedTasks
        numberOfOverdueTasks = numOverdueTasks
        fName = first
        lName = last
        username = uName
        password = pass
        isNull = noValue
    }

    // Constructor with username and password
    constructor(username: String?, password: String?) {
        this.username = username
        this.password = password
    }

    fun getFullName(): String {
        return "$fName $lName"
    }

    fun getUserName(): String
    {
        return "$fName[0].$lName"
    }

    fun getEmail(): String
    {
        return "$fName[0].$lName@shenefelt.net"
    }

    override fun toString(): String
    {
        return if (isNull) "null" else "User { Name=${ getFullName() } Email=${ getEmail() } }"
    }
}
