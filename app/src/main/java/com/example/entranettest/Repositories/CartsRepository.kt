package com.example.entranettest.Repositories

import com.example.entranettest.DAO.CartDao
import com.example.entranettest.Entities.toDomain
import com.example.entranettest.Entities.toEntity
import com.example.entranettest.Interfaces.ApiService
import com.example.entranettest.JsonModels.Cart
import com.example.entranettest.JsonModels.domain
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartsRepository @Inject constructor(
    private val api: ApiService,
    private val cartDao: CartDao
) {

    fun observeCarts(): Flow<List<Cart>> =
        cartDao.getCartsWithProducts().map { list -> list.map { it.toDomain() } }

    suspend fun refreshCarts() {
        val dto = api.getCarts()        // CartsResponseDto
        val response = dto.domain()     // CartsResponse (domain)
        val carts = response.carts      // List<Cart>

        cartDao.clearCartProducts()
        cartDao.clearCarts()

        cartDao.insertCarts(carts.map { it.toEntity() })
        val allProducts = carts.flatMap { cart ->
            cart.products.map { prod -> prod.toEntity(cartId = cart.id) }
        }
        cartDao.insertCartProducts(allProducts)
    }
}
