package com.kbomber.types

data class Phone(
    val countryCode: CountryCode,
    val body: PhoneBody
) {
    @JvmInline
    value class CountryCode(val int: Int)

    @JvmInline
    value class PhoneBody(val string: String)
}