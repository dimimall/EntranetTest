package com.example.entranettest

import com.example.entranettest.JsonModels.CartsResponseDto
import com.example.entranettest.JsonModels.UserResponseDto
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val gson = Gson()

    @Test
    fun `parse user response JSON into UserResponseDto`() {
        // this is a MINIMAL sample, you can paste one of your real user objects if you want
        val json = """
            {
              "users": [
                {
                  "id": 1,
                  "firstName": "Emily",
                  "lastName": "Johnson",
                  "maidenName": "Smith",
                  "age": 29,
                  "gender": "female",
                  "email": "emily.johnson@x.dummyjson.com",
                  "phone": "+81 965-431-3024",
                  "username": "emilys",
                  "password": "emilyspass",
                  "birthDate": "1996-5-30",
                  "image": "https://dummyjson.com/icon/emilys/128",
                  "bloodGroup": "O-",
                  "height": 193.24,
                  "weight": 63.16,
                  "eyeColor": "Green",
                  "hair": {
                    "color": "Brown",
                    "type": "Curly"
                  },
                  "ip": "42.48.100.32",
                  "address": {
                    "address": "626 Main Street",
                    "city": "Phoenix",
                    "state": "Mississippi",
                    "stateCode": "MS",
                    "postalCode": "29112",
                    "coordinates": {
                      "lat": -77.16213,
                      "lng": -92.084824
                    },
                    "country": "United States"
                  },
                  "macAddress": "47:fa:41:18:ec:eb",
                  "university": "University of Wisconsin--Madison",
                  "bank": {
                    "cardExpire": "05/28",
                    "cardNumber": "3693233511855044",
                    "cardType": "Diners Club International",
                    "currency": "GBP",
                    "iban": "GB74MH2UZLR9TRPHYNU8F8"
                  },
                  "company": {
                    "department": "Engineering",
                    "name": "Dooley, Kozey and Cronin",
                    "title": "Sales Manager",
                    "address": {
                      "address": "263 Tenth Street",
                      "city": "San Francisco",
                      "state": "Wisconsin",
                      "stateCode": "WI",
                      "postalCode": "37657",
                      "coordinates": {
                        "lat": 71.814525,
                        "lng": -161.150263
                      },
                      "country": "United States"
                    }
                  },
                  "ein": "977-175",
                  "ssn": "900-590-289",
                  "userAgent": "Mozilla/5.0 (...snip...)",
                  "crypto": {
                    "coin": "Bitcoin",
                    "wallet": "0xb9fc2fe63b2a6c003f1c324c3bfa53259162181a",
                    "network": "Ethereum (ERC20)"
                  },
                  "role": "admin"
                }
              ],
              "total": 208,
              "skip": 0,
              "limit": 30
            }
        """.trimIndent()

        val response = gson.fromJson(json, UserResponseDto::class.java)

        assertNotNull(response)
        assertEquals(208, response.total)
        assertEquals(0, response.skip)
        assertEquals(30, response.limit)
        assertNotNull(response.users)
        assertEquals(1, response.users!!.size)

        val user = response.users!![0]
        assertEquals(1, user.id)
        assertEquals("Emily", user.firstName)
        assertEquals("Johnson", user.lastName)
        assertEquals("Smith", user.maidenName)
        assertEquals(29, user.age)
        assertEquals("female", user.gender)
        assertEquals("emily.johnson@x.dummyjson.com", user.email)
        assertEquals("+81 965-431-3024", user.phone)
        assertEquals("emilys", user.username)
        assertEquals("emilyspass", user.password)
        assertEquals("1996-5-30", user.birthDate)
        assertEquals("https://dummyjson.com/icon/emilys/128", user.image)
        assertEquals("O-", user.bloodGroup)
        assertEquals(193.24, user.height!!, 0.0)
        assertEquals(63.16, user.weight!!, 0.0)
        assertEquals("Green", user.eyeColor)

        // nested hair
        assertNotNull(user.hair)
        assertEquals("Brown", user.hair!!.color)
        assertEquals("Curly", user.hair!!.type)

        // nested address
        assertNotNull(user.address)
        val addr = user.address!!
        assertEquals("626 Main Street", addr.address)
        assertEquals("Phoenix", addr.city)
        assertEquals("Mississippi", addr.state)
        assertEquals("MS", addr.stateCode)
        assertEquals("29112", addr.postalCode)
        assertEquals("United States", addr.country)
        assertNotNull(addr.coordinates)
        assertEquals(-77.16213, addr.coordinates!!.lat!!, 0.0)
        assertEquals(-92.084824, addr.coordinates!!.lng!!, 0.0)

        // nested bank
        assertNotNull(user.bank)
        assertEquals("05/28", user.bank!!.cardExpire)
        assertEquals("3693233511855044", user.bank!!.cardNumber)
        assertEquals("Diners Club International", user.bank!!.cardType)
        assertEquals("GBP", user.bank!!.currency)
        assertEquals("GB74MH2UZLR9TRPHYNU8F8", user.bank!!.iban)

        // crypto
        assertNotNull(user.crypto)
        assertEquals("Bitcoin", user.crypto!!.coin)
        assertEquals("Ethereum (ERC20)", user.crypto!!.network)

        assertEquals("admin", user.role)
    }

    @Test
    fun `parse carts response JSON into CartsResponseDto`() {
        val json = """
            {
              "carts": [
                {
                  "id": 1,
                  "products": [
                    {
                      "id": 168,
                      "title": "Charger SXT RWD",
                      "price": 32999.99,
                      "quantity": 3,
                      "total": 98999.97,
                      "discountPercentage": 13.39,
                      "discountedTotal": 85743.87,
                      "thumbnail": "https://cdn.dummyjson.com/products/images/vehicle/Charger%20SXT%20RWD/thumbnail.png"
                    },
                    {
                      "id": 78,
                      "title": "Apple MacBook Pro 14 Inch Space Grey",
                      "price": 1999.99,
                      "quantity": 2,
                      "total": 3999.98,
                      "discountPercentage": 18.52,
                      "discountedTotal": 3259.18,
                      "thumbnail": "https://cdn.dummyjson.com/products/images/laptops/Apple%20MacBook%20Pro%2014%20Inch%20Space%20Grey/thumbnail.png"
                    }
                  ],
                  "total": 103774.85,
                  "discountedTotal": 89686.65,
                  "userId": 33,
                  "totalProducts": 4,
                  "totalQuantity": 15
                }
              ],
              "total": 50,
              "skip": 0,
              "limit": 30
            }
        """.trimIndent()

        val response = gson.fromJson(json, CartsResponseDto::class.java)

        assertNotNull(response)
        assertEquals(50, response.total)
        assertEquals(0, response.skip)
        assertEquals(30, response.limit)

        assertNotNull(response.carts)
        assertEquals(1, response.carts!!.size)

        val cart = response.carts!![0]
        assertEquals(1, cart.id)
        assertEquals(33, cart.userId)
        assertEquals(103774.85, cart.total!!, 0.0)
        assertEquals(89686.65, cart.discountedTotal!!, 0.0)
        assertEquals(4, cart.totalProducts)
        assertEquals(15, cart.totalQuantity)

        assertNotNull(cart.products)
        assertEquals(2, cart.products!!.size)

        val product0 = cart.products!![0]
        assertEquals(168, product0.id)
        assertEquals("Charger SXT RWD", product0.title)
        assertEquals(32999.99, product0.price!!, 0.0)
        assertEquals(3, product0.quantity)
        assertEquals(98999.97, product0.total!!, 0.0)
        assertEquals(13.39, product0.discountPercentage!!, 0.0)
        assertEquals(85743.87, product0.discountedTotal!!, 0.0)
        assertEquals(
            "https://cdn.dummyjson.com/products/images/vehicle/Charger%20SXT%20RWD/thumbnail.png",
            product0.thumbnail
        )
    }
}