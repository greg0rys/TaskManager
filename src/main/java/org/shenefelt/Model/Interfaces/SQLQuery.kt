package org.shenefelt.Model.Interfaces
interface SQLQuery
{
    fun selectAllUsers(): String
    fun selectAllTasks(): String
    fun selectUserById(userId: Int): String
    fun selectUserAuths(): String
    fun insertNewUser(): String

}