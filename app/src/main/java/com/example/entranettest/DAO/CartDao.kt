package com.example.entranettest.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.entranettest.Entities.CartEntity
import com.example.entranettest.Entities.CartProductEntity
import com.example.entranettest.Entities.CartWithProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Transaction
    @Query("SELECT * FROM carts")
    fun getCartsWithProducts(): Flow<List<CartWithProducts>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarts(carts: List<CartEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartProducts(products: List<CartProductEntity>)

    @Query("DELETE FROM carts")
    suspend fun clearCarts()

    @Query("DELETE FROM cart_products")
    suspend fun clearCartProducts()
}