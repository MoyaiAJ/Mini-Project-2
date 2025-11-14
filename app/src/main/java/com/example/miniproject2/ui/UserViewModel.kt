package com.example.miniproject2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniproject2.data.UserRepository
import com.example.miniproject2.data.local.AppDatabase
import com.example.miniproject2.data.model.User
import com.example.miniproject2.data.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val users: StateFlow<List<User>> = searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                repository.users
            } else {
                repository.searchUsers(query)
            }
        }
        .stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())

    init {
        val userDao = AppDatabase.Companion.getDatabase(application).userDao()
        val apiService = ApiService.Companion.create()
        repository = UserRepository(apiService, userDao)
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            repository.refreshUsers()
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }
}