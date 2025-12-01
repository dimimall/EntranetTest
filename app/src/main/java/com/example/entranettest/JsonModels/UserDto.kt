package com.example.entranettest.JsonModels

import android.R.attr.country
import com.google.gson.annotations.SerializedName


data class UserResponseDto(
    @SerializedName("users")
    val users: List<UserDto>?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("limit")
    val limit: Int?
)

data class UserDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("maidenName")
    val maidenName: String?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("birthDate")
    val birthDate: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("bloodGroup")
    val bloodGroup: String?,
    @SerializedName("height")
    val height: Double?,
    @SerializedName("weight")
    val weight: Double?,
    @SerializedName("eyeColor")
    val eyeColor: String?,
    @SerializedName("hair")
    val hair: HairDto?,
    @SerializedName("ip")
    val ip: String?,
    @SerializedName("address")
    val address: AddressDto?,
    @SerializedName("macAddress")
    val macAddress: String?,
    @SerializedName("university")
    val university: String?,
    @SerializedName("bank")
    val bank: BankDto?,
    @SerializedName("company")
    val company: CompanyDto?,
    @SerializedName("ein")
    val ein: String?,
    @SerializedName("ssn")
    val ssn: String?,
    @SerializedName("userAgent")
    val userAgent: String?,
    @SerializedName("crypto")
    val crypto: CryptoDto?,
    @SerializedName("role")
    val role: String?
)

data class HairDto (
    @SerializedName("color")
    val color: String?,
    @SerializedName("type")
    val type: String?
)

data class AddressDto (
    @SerializedName("address")
    val address: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("coordinates")
    val coordinates: CoordinatesDto?,
    @SerializedName("postalCode")
    val postalCode: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("stateCode")
    val stateCode: String?,
    @SerializedName("country")
    val country: String?
)

data class BankDto(
    @SerializedName("cardExpire")
    val cardExpire: String?,
    @SerializedName("cardNumber")
    val cardNumber: String?,
    @SerializedName("cardType")
    val cardType: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("iban")
    val iban: String?
)

data class CompanyDto(
    @SerializedName("address")
    val address: AddressDto?,
    @SerializedName("department")
    val department: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("title")
    val title: String?
)

data class CryptoDto(
    @SerializedName("coin")
    val coin: String?,
    @SerializedName("network")
    val network: String?,
    @SerializedName("wallet")
    val wallet: String?
)

data class CoordinatesDto(
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lng")
    val lng: Double?
)

fun UserResponseDto.domain(): UsersResponse {
    require(users != null) { "users cannot be null" }
    require(total != null) { "total cannot be null" }
    require(skip != null) { "skip cannot be null" }
    require(limit != null) { "limit cannot be null" }

    return UsersResponse(
        users = users.map { it.domain() },
        total = total,
        skip = skip,
        limit = limit
    )
}


fun UserDto.domain(): User {
    require(id != null) { "id cannot be null" }
    require(firstName != null) { "firstName cannot be null" }
    require(lastName != null) { "lastName cannot be null" }
    require(maidenName != null) { "maidenName cannot be null" }
    require(age != null) { "age cannot be null" }
    require(gender != null) { "gender cannot be null" }
    require(email != null) { "email cannot be null" }
    require(phone != null) { "phone cannot be null" }
    require(username != null) { "username cannot be null" }
    require(password != null) { "password cannot be null" }
    require(birthDate != null) { "birthDate cannot be null" }
    require(image != null) { "image cannot be null" }
    require(bloodGroup != null) { "bloodGroup cannot be null" }
    require(height != null) { "height cannot be null" }
    require(weight != null) { "weight cannot be null"}
    require(eyeColor != null) { "eyeColor cannot be null" }
    require(hair != null) { "hair cannot be null" }
    require(ip != null) { "ip cannot be null" }
    require(address != null) { "address cannot be null" }
    require(macAddress != null) { "macAddress cannot be null" }
    require(university != null) { "university cannot be null" }
    require(bank != null) { "bank cannot be null" }
    require(company != null) { "company cannot be null" }
    require(ein != null) { "ein cannot be null" }
    require(ssn != null) { "ssn cannot be null"}
    require(userAgent != null) { "userAgent cannot be null" }
    require(crypto != null) { "crypto cannot be null" }
    require(role != null) { "role cannot be null" }

    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        maidenName = maidenName,
        age = age,
        gender = gender,
        email = email,
        phone = phone,
        username = username,
        password = password,
        birthDate = birthDate,
        image = image,
        bloodGroup = bloodGroup,
        height = height,
        weight = weight,
        eyeColor = eyeColor,
        hair = hair.domain(),
        ip = ip,
        address = address.domain(),
        macAddress = macAddress,
        university = university,
        bank = bank.domain(),
        company = company.domain(),
        ein = ein,
        ssn = ssn,
        userAgent = userAgent,
        crypto = crypto.domain(),
        role = role)

}

fun HairDto.domain(): Hair {
    require(color != null) { "color cannot be null" }
    require(type != null) { "type cannot be null" }

    return Hair(
        color = color,
        type = type)
}

fun AddressDto.domain(): Address {
    require(address != null) { "address cannot be null" }
    require(city != null) { "city cannot" }
    require(state != null) { "state cannot be null" }
    require(stateCode != null) { "stateCode cannot be null" }
    require(postalCode != null) { "postalCode cannot be null" }
    require(coordinates != null) { "coordinates cannot be null" }
    require(country != null) { "country cannot be null" }



    return Address(
        address = address,
        city = city,
        coordinates = coordinates.domain(),
        stateCode = stateCode,
        postalCode = postalCode,
        country = country,
        state = state
    )
}

fun BankDto.domain(): Bank {
    require(cardExpire != null) { "cardExpire cannot be null" }
    require(cardNumber != null) { "cardNumber cannot be null" }
    require(cardType != null) { "cardType" }
    require(currency != null) { "currency cannot be null" }
    require(iban != null) { "iban cannot be null" }

    return Bank(
        cardExpire = cardExpire,
        cardNumber = cardNumber,
        cardType = cardType,
        currency = currency,
        iban = iban
    )
}

fun CompanyDto.domain(): Company {
    require(department != null) { "department" }
    require(name != null) { "name cannot be null" }
    require(title != null) { "title" }

    return Company(
        department = department,
        name = name,
        title = title,
        address = address?.domain()
    )
}

fun CryptoDto.domain(): Crypto {
    require(coin != null) { "coin cannot be null" }
    require(network != null) { "network cannot be null" }
    require(wallet != null) { "wallet cannot be null" }

    return Crypto(
        coin = coin,
        network = network,
        wallet = wallet)
}

fun CoordinatesDto.domain(): Coordinates {
    require(lat != null) { "lat cannot be null" }
    require(lng != null) { "lng" }

    return Coordinates(
        lat = lat,
        lng = lng)
}