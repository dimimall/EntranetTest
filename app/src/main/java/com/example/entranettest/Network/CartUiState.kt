package com.example.entranettest.Network

import com.example.entranettest.JsonModels.CartsResponse

sealed class CartUiState {
    object Loading : CartUiState()
    data class Success(val data: CartsResponse) : CartUiState()
    data class Error(val message: String) : CartUiState()
}
