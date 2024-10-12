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
    var isAdmin: Boolean = false
    var tasks: Map<Int, Task> = HashMap() // tasks sorted by priority
    var isLoggedIn: Boolean = false

    // Default constructor
    constructor() {
        isAdmin = false
    }


    // Copy constructor
    constructor(user: User)
    {
        id = user.id
        numberOfTasks = user.numberOfTasks
        numberOfCompletedTasks = user.numberOfCompletedTasks
        numberOfOverdueTasks = user.numberOfOverdueTasks
        fName = user.fName
        lName = user.lName
        password = user.password
        username = user.username
        isAdmin = user.isAdmin
        tasks = HashMap(user.tasks)
    }

    // Constructor with all fields
    constructor(
        dbID: Int, numTasks: Int, numCompletedTasks: Int,
        numOverdueTasks: Int, first: String?, last: String?,
        uName: String?, pass: String?
    ) {
        id = dbID
        numberOfTasks = numTasks
        numberOfCompletedTasks = numCompletedTasks
        numberOfOverdueTasks = numOverdueTasks
        fName = first
        lName = last
        username = uName
        password = pass
    }

    // Constructor with username and password
    constructor(username: String?, password: String?)
    {
        this.username = username
        this.password = password
    }

    fun getFullName(): String {
        return "$fName $lName"
    }

    fun getUsername(): String
    {
        return "$fName[0].$lName"
    }

    fun getEmail(): String
    {
        return "$fName[0].$lName@shenefelt.net"
    }


    override fun toString(): String
    {
        return "User { Name=${ getFullName() } Email=${ getEmail() } Username=${ getUsername() }}"
    }
}
