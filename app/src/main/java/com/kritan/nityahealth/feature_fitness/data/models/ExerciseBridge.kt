package com.kritan.nityahealth.feature_fitness.data.models

import com.google.gson.annotations.SerializedName

data class ExerciseBridge(

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("post_id")
    val postId: Int? = null,

    @SerializedName("post")
    val post: String? = null,

    @SerializedName("package")
    val mPackage: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null


)