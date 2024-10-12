package org.shenefelt.Controller.Manager

import org.shenefelt.Controller.Repository.UserRepo
import org.shenefelt.Model.User

class UserManager {

    private val userRepo = UserRepo()
    private val userList = mutableListOf<User>()

    @Throws(Exception::class)
    constructor() {
        userRepo.getAllUsers(userList)
    }

    constructor(users: List<User>?) {
        if (users == null) return
        userList.addAll(users)
    }

    /**
     * Add a user to the list and the DB
     * @param user the user we are going to add
     * @return true if we added, false if there was a problem
     */
    fun addUser(user: User): Boolean {
        return if (userRepo.addUser(user)) {
            userList.add(user)
        } else {
            false // If we make it here there was some kind of issue somewhere -_-
        }
    }

    /**
     * Update the user here in the local list and also push the DB update.
     * @param user the user we are going to update
     * @return true if we updated, false if there was a problem.
     */
    fun updateUser(user: User): Boolean {
        userList.firstOrNull { it.id == user.id }?.let {
            it.username = user.username
            it.password = user.password
            // Update other fields as necessary
        }
        return userRepo.updateUser(user)
    }


    /**
     * Select a user from the list by their ID
     * @param id the user we want
     * @return the user, or a null user.
     */
    fun getUserByID(id: Int): User {
        return userList.firstOrNull { it.id == id } ?: User()
    }
}
