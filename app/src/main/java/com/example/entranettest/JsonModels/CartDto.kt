package com.example.entranettest.JsonModels

import com.google.gson.annotations.SerializedName

class CartDto {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("products")
    val products: List<CartProductDto>? = null

    @SerializedName("total")
    val total: Double? = null

    @SerializedName("discountedTotal")
    val discountedTotal: Double? = null

    @SerializedName("userId")
    val userId: Int? = null

    @SerializedName("totalProducts")
    val totalProducts: Int? = null

    @SerializedName("totalQuantity")
    val totalQuantity: Int? = null

}

class CartProductDto {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("price")
    val price: Double? = null

    @SerializedName("quantity")
    val quantity: Int? = null

    @SerializedName("total")
    val total: Double? = null

    @SerializedName("discountPercentage")
    val discountPercentage: Double? = null

    @SerializedName("discountedTotal")
    val discountedTotal: Double? = null

    @SerializedName("thumbnail")
    val thumbnail: String? = null
}

class CartsResponseDto {
    @SerializedName("carts")
    val carts: List<CartDto>? = null

    @SerializedName("total")
    val total: Int? = null

    @SerializedName("skip")
    val skip: Int? = null

    @SerializedName("limit")
    val limit: Int? = null
}

fun CartsResponseDto.domain(): CartsResponse {
    require(carts != null) { "carts cannot be null" }
    require(total != null) { "total cannot be null" }
    require(skip != null) { "skip cannot be null" }
    require(limit != null) { "limit cannot be null" }

    return CartsResponse(
        carts = carts.map { it.domain() },
        total = total,
        skip = skip,
        limit = limit
    )
}

fun CartDto.domain(): Cart {
    require(id != null) { "id cannot be null" }
    require(products != null) { "products cannot be null" }
    require(total != null) { "total cannot be null" }
    require(discountedTotal != null) { "discountedTotal cannot be null" }
    require(userId != null) { "userId cannot be null" }
    require(totalProducts != null) { "totalProducts cannot be null" }
    require(totalQuantity != null) { "totalQuantity cannot be null" }

    return Cart(
        id = id,
        products = products.map { it.domain() },
        total = total,
        discountedTotal = discountedTotal,
        userId = userId,
        totalProducts = totalProducts,
        totalQuantity = totalQuantity
    )
}

fun CartProductDto.domain(): CartProduct {
    require(id != null) { "id cannot be null" }
    require(title != null) { "title cannot be null" }
    require(price != null) { "price cannot be null" }
    require(quantity != null) { "quantity cannot be null" }
    require(total != null) { "total cannot be null" }
    require(discountPercentage != null) { "discountPercentage cannot be null" }
    require(discountedTotal != null) { "discountedTotal cannot be null" }
    require(thumbnail != null) { "thumbnail cannot be null" }

    return CartProduct(
        id = id,
        title = title,
        price = price,
        quantity = quantity,
        total = total,
        discountPercentage = discountPercentage,
        discountedTotal = discountedTotal,
        thumbnail = thumbnail
    )
}