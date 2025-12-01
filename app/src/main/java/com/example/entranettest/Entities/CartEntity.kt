package com.example.entranettest.Entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.entranettest.JsonModels.Cart
import com.example.entranettest.JsonModels.CartProduct

@Entity(tableName = "carts")
data class CartEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val total: Double,
    val discountedTotal: Double,
    val totalProducts: Int,
    val totalQuantity: Int
)

@Entity(tableName = "cart_products")
data class CartProductEntity(
    @PrimaryKey(autoGenerate = true) val localId: Long = 0,  // local PK
    val cartId: Int,                                         // FK to CartEntity.id
    val productId: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val discountPercentage: Double,
    val discountedTotal: Double,
    val thumbnail: String
)

data class CartWithProducts(
    @Embedded val cart: CartEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cartId"
    )
    val products: List<CartProductEntity>
)


fun Cart.toEntity(): CartEntity =
    CartEntity(
        id = id,
        userId = userId,
        total = total,
        discountedTotal = discountedTotal,
        totalProducts = totalProducts,
        totalQuantity = totalQuantity
    )

fun CartProduct.toEntity(cartId: Int): CartProductEntity =
    CartProductEntity(
        cartId = cartId,
        productId = id,
        title = title,
        price = price,
        quantity = quantity,
        total = total,
        discountPercentage = discountPercentage,
        discountedTotal = discountedTotal,
        thumbnail = thumbnail
    )

fun CartWithProducts.toDomain(): Cart =
    Cart(
        id = cart.id,
        userId = cart.userId,
        total = cart.total,
        discountedTotal = cart.discountedTotal,
        totalProducts = cart.totalProducts,
        totalQuantity = cart.totalQuantity,
        products = products.map {
            CartProduct(
                id = it.productId,
                title = it.title,
                price = it.price,
                quantity = it.quantity,
                total = it.total,
                discountPercentage = it.discountPercentage,
                discountedTotal = it.discountedTotal,
                thumbnail = it.thumbnail
            )
        }
    )