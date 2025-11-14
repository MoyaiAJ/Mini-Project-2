package com.example.miniproject2.data

import com.example.miniproject2.data.local.UserDao
import com.example.miniproject2.data.model.User
import com.example.miniproject2.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val apiService: ApiService,
    private val userDao: UserDao
) {

    val users = userDao.getAllUsers()

    suspend fun refreshUsers() {
        withContext(Dispatchers.IO) {
            try {
                val fetchedUsers = apiService.getUsers()
                userDao.insertUsers(fetchedUsers)
            } catch (e: Exception) {
            }
        }
    }
    fun searchUsers(query: String) = userDao.searchUsers(query)
}