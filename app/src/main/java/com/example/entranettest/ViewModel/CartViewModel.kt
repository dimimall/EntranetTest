package com.example.entranettest.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entranettest.Network.CartUiState
import com.example.entranettest.Repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: MainRepository): ViewModel()
{
    private val _cartState = MutableLiveData<CartUiState>()
    val cartState: LiveData<CartUiState> = _cartState

    init {
        loadCarts()
    }

    fun loadCarts(limit: Int = 30, skip: Int = 0) {
        _cartState.value = CartUiState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getCarts(limit, skip)
                _cartState.value = CartUiState.Success(response)
            } catch (e: Exception) {
                _cartState.value = CartUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}