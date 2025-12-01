package com.example.entranettest.Entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.entranettest.JsonModels.Address
import com.example.entranettest.JsonModels.Bank
import com.example.entranettest.JsonModels.Company
import com.example.entranettest.JsonModels.Coordinates
import com.example.entranettest.JsonModels.Crypto
import com.example.entranettest.JsonModels.Hair
import com.example.entranettest.JsonModels.User

@Entity(tableName = "users")
data class UserEntity(@PrimaryKey val id: Int,
                      val firstName: String,
                      val lastName: String,
                      val maidenName: String,
                      val age: Int,
                      val gender: String,
                      val email: String,
                      val phone: String,
                      val username: String,
                      val password: String,
                      val birthDate: String,
                      val image: String,
                      val bloodGroup: String,
                      val height: Double,
                      val weight: Double,
                      val eyeColor: String,
                      @Embedded(prefix = "hair_")
                      val hair: HairEntity,
                      val ip: String,
                      @Embedded(prefix = "address_")
                      val address: AddressEntity,
                      val macAddress: String,
                      val university: String,
                      @Embedded(prefix = "bank_")
                      val bank: BankEntity,
                      @Embedded(prefix = "company_")
                      val company: CompanyEntity,
                      val ein: String,
                      val ssn: String,
                      val userAgent: String,
                      @Embedded(prefix = "crypto_")
                      val crypto: CryptoEntity,
                      val role: String

    )

data class HairEntity(
    val color: String,
    val type: String
)

data class CoordinatesEntity(
    val lat: Double,
    val lng: Double
)

data class AddressEntity(
    val address: String,
    val city: String,
    @Embedded(prefix = "coord_")
    val coordinates: CoordinatesEntity,
    val postalCode: String,
    val state: String,
    val stateCode: String,
    val country: String
)

data class BankEntity(
    val cardExpire: String,
    val cardNumber: String,
    val cardType: String,
    val currency: String,
    val iban: String
)

data class CompanyEntity(
    val department: String,
    val name: String,
    val title: String,
    @Embedded(prefix = "company_addr_")
    val address: AddressEntity?
)

data class CryptoEntity(
    val coin: String,
    val network: String,
    val wallet: String
)

fun User.toEntity(): UserEntity =
    UserEntity(
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
        hair = hair.toEntity(),
        ip = ip,
        address = address.toEntity(),
        macAddress = macAddress,
        university = university,
        bank = bank.toEntity(),
        company = company.toEntity(),
        ein = ein,
        ssn = ssn,
        userAgent = userAgent,
        crypto = crypto.toEntity(),
        role = role
    )

fun Hair.toEntity(): HairEntity =
    HairEntity(
        color = color,
        type = type
    )

fun Coordinates.toEntity(): CoordinatesEntity =
    CoordinatesEntity(
        lat = lat,
        lng = lng
    )

fun Address.toEntity(): AddressEntity =
    AddressEntity(
        address = address,
        city = city,
        coordinates = coordinates.toEntity(),
        postalCode = postalCode,
        state = state,
        stateCode = stateCode,
        country = country
    )

fun Bank.toEntity(): BankEntity =
    BankEntity(
        cardExpire = cardExpire,
        cardNumber = cardNumber,
        cardType = cardType,
        currency = currency,
        iban = iban
    )

fun Company.toEntity(): CompanyEntity =
    CompanyEntity(
        department = department,
        name = name,
        title = title,
        address = address?.toEntity()
    )

fun Crypto.toEntity(): CryptoEntity =
    CryptoEntity(
        coin = coin,
        network = network,
        wallet = wallet
    )

// ENTITY -> DOMAIN

fun UserEntity.toDomain(): User =
    User(
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
        hair = hair.toDomain(),
        ip = ip,
        address = address.toDomain(),
        macAddress = macAddress,
        university = university,
        bank = bank.toDomain(),
        company = company.toDomain(),
        ein = ein,
        ssn = ssn,
        userAgent = userAgent,
        crypto = crypto.toDomain(),
        role = role
    )

fun HairEntity.toDomain(): Hair =
    Hair(
        color = color,
        type = type
    )

fun CoordinatesEntity.toDomain(): Coordinates =
    Coordinates(
        lat = lat,
        lng = lng
    )

fun AddressEntity.toDomain(): Address =
    Address(
        address = address,
        city = city,
        coordinates = coordinates.toDomain(),
        postalCode = postalCode,
        state = state,
        stateCode = stateCode,
        country = country
    )

fun BankEntity.toDomain(): Bank =
    Bank(
        cardExpire = cardExpire,
        cardNumber = cardNumber,
        cardType = cardType,
        currency = currency,
        iban = iban
    )

fun CompanyEntity.toDomain(): Company =
    Company(
        department = department,
        name = name,
        title = title,
        address = address?.toDomain()
    )

fun CryptoEntity.toDomain(): Crypto =
    Crypto(
        coin = coin,
        network = network,
        wallet = wallet
    )