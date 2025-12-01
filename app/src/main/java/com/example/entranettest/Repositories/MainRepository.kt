package com.example.entranettest.Repositories

import com.example.entranettest.Interfaces.ApiService
import com.example.entranettest.JsonModels.CartsResponse
import com.example.entranettest.JsonModels.CartsResponseDto
import com.example.entranettest.JsonModels.UserResponseDto
import com.example.entranettest.JsonModels.UsersResponse
import com.example.entranettest.JsonModels.domain
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUsers(limit: Int = 30, skip: Int = 0): UsersResponse {
        val dto: UserResponseDto = apiService.getUsers(limit, skip)
        return dto.domain()
    }

    suspend fun getCarts(limit: Int = 30, skip: Int = 0): CartsResponse {
        val dto: CartsResponseDto = apiService.getCarts(limit, skip)
        return dto.domain()
    }
}

