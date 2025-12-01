package com.example.entranettest.Network

import com.example.entranettest.JsonModels.UsersResponse

sealed class UsersUiState {
    object Loading : UsersUiState()
    data class Success(val data: UsersResponse) : UsersUiState()
    data class Error(val message: String) : UsersUiState()
}