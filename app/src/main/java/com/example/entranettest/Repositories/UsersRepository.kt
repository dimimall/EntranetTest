package com.example.entranettest.Repositories

import com.example.entranettest.DAO.UserDao
import com.example.entranettest.Entities.toDomain
import com.example.entranettest.Entities.toEntity
import com.example.entranettest.Interfaces.ApiService
import com.example.entranettest.JsonModels.User
import com.example.entranettest.JsonModels.domain
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UsersRepository @Inject constructor(
    private val api: ApiService,
    private val userDao: UserDao
){
    fun observeUsers(): Flow<List<User>> =
        userDao.getAllUsers().map { list -> list.map { it.toDomain() } }

    suspend fun refreshUsers() {
        val dto = api.getUsers()        // UserResponseDto
        val response = dto.domain()     // UsersResponse (domain)
        val users = response.users      // List<User>

        userDao.clearUsers()
        userDao.insertUsers(users.map { it.toEntity() })
    }

}