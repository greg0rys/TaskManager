package org.shenefelt

import org.shenefelt.Controller.Manager.CredentialManager
import org.shenefelt.Controller.Manager.TaskManager
import org.shenefelt.Controller.Manager.UserManager
import org.shenefelt.Controller.Repository.UserRepo
import org.shenefelt.Helpers.InputCollector
import org.shenefelt.Model.User

// Drives the program and menus
class Driver @Throws(Exception::class) constructor() {
    private var storedHashedPassword: String = ""
    private var currentUser: User? = null
    private val usersInfo: MutableMap<String, String> = mutableMapOf()


    private val userManager: UserManager = UserManager()
    private val taskManager: TaskManager = TaskManager()
    private val userRepo: UserRepo = UserRepo()

    init {
        userRepo.getAllUsersAuthInfo(usersInfo)
        login()
    }

    private fun login() {
        if (usersInfo.isEmpty()) return

        val temp = InputCollector.collectUserInfo()
        storedHashedPassword = usersInfo[temp.username] ?: ""

        if (storedHashedPassword.isEmpty()) {
            println("Login failed { Username Not Found! } Please try again!")
            login()
            return
        }

        if (CredentialManager.checkPass(temp.password, storedHashedPassword)) {
            currentUser = temp
            mainMenu()
            return
        }

        println("Login failed { Incorrect Password! } Please try again!")
        login()
    }

    private fun mainMenu() {
        println("Main Menu\n")
        println("Welcome ${currentUser?.username} ")
        // Give admin menu if isAdmin()
        println("1. Task Menu")
        println("2. Exit")

        processMainMenuChoice(InputCollector.collectMenuChoice())
    }

    private fun processMainMenuChoice(choice: Int) {
        when (choice) {
            1 -> {
                mainMenu()
            }
            2 -> {
                return;
            }
            else -> {
                println("Invalid menu choice! Try again")
                mainMenu()
            }
        }
    }


    private fun adminMenu(U: User)
    {

    }

}
