package com.kritan.nityahealth.auth.data.models

import com.google.gson.annotations.SerializedName

data class FacebookUserData(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("gender")
    val gender: String? = null,

    @SerializedName("hometown")
    val location: String? = null,

    @SerializedName("picture")
    val picture: FacebookPictureData? = FacebookPictureData()

)

data class FacebookPictureData(
    @SerializedName("data")
    val data: FacebookPicture? = FacebookPicture()
)

data class FacebookPicture(
    @SerializedName("height")
    val height: Int? = null,

    @SerializedName("is_silhouette")
    val isSilhouette: Boolean? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("width")
    val width: Int? = null

)