package com.kritan.nityahealth.feature_user.data.models

data class UserData(
    val name: String,
    val gender: String,
    val address: String,
    val contact: String,
    val email: String,
    val image: String
) {
    companion object {
        val defaultUser = UserData(
            name = "name",
            gender = "gender",
            address = "address",
            contact = "contact",
            email = "email",
            image = "image",
        )
    }
}