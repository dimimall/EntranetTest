package com.example.entranettest.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.entranettest.Entities.UserEntity
import com.example.entranettest.DAO.UserDao
import com.example.entranettest.Entities.CartEntity
import com.example.entranettest.Entities.CartProductEntity
import com.example.entranettest.DAO.CartDao

@Database(
    entities = [
        UserEntity::class,
        CartEntity::class,
        CartProductEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cartDao(): CartDao
}