package com.example.entranettest.Interfaces

import com.example.entranettest.JsonModels.CartsResponse
import com.example.entranettest.JsonModels.CartsResponseDto
import com.example.entranettest.JsonModels.UserResponseDto
import com.example.entranettest.JsonModels.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(@Query("limit") limit: Int = 30,@Query("skip") skip: Int = 0): UserResponseDto

    @GET("carts")
    suspend fun getCarts(@Query("limit") limit: Int = 30,@Query("skip") skip: Int = 0): CartsResponseDto

}