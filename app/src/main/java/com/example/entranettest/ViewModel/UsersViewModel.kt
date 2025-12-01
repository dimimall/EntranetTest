package com.example.entranettest.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entranettest.JsonModels.UsersResponse
import com.example.entranettest.Network.UsersUiState
import com.example.entranettest.Repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import java.lang.Exception

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: MainRepository) : ViewModel(){

    private val _usersState = MutableLiveData<UsersUiState>()
    val usersState: LiveData<UsersUiState> = _usersState

    init {
        loadUsers()
    }

    fun loadUsers(limit: Int = 30, skip: Int = 0) {
        _usersState.value = UsersUiState.Loading

        viewModelScope.launch {
            try {
                val response: UsersResponse = repository.getUsers(limit, skip)
                _usersState.value = UsersUiState.Success(response)
            } catch (e: Exception) {
                _usersState.value =
                    UsersUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}