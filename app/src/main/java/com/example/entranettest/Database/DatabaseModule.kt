package com.example.entranettest.Database

import android.content.Context
import androidx.room.Room
import com.example.entranettest.Database.AppDatabase
import com.example.entranettest.DAO.CartDao
import com.example.entranettest.DAO.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "entranet_db"
        ).build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideCartDao(db: AppDatabase): CartDao = db.cartDao()
}