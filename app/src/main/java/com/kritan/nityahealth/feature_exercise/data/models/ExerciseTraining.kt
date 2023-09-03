package com.kritan.nityahealth.feature_exercise.data.models

import com.google.gson.annotations.SerializedName

data class ExerciseTraining (
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("package_id")
    var packageId: Int? = null,

    @SerializedName("post")
    var post: String? = null,

    @SerializedName("package")
    var mPackage: String? = null,

    @SerializedName("trainingname")
    var trainingName: String? = null,

    @SerializedName("slug")
    var slug: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("duration")
    var duration: String? = null,

    @SerializedName("order_id")
    var orderId: Int? = null,

    @SerializedName("gif")
    var gif: String? = null,

    @SerializedName("calorie_burn")
    var calorieBurn: String? = null,

    @SerializedName("difficulty")
    var difficulty: String? = null,

    @SerializedName("equipments")
    var equipments: String? = null,

    @SerializedName("training_type")
    var trainingType: String? = null,

    @SerializedName("timer")
    var timer: String? = null,

    @SerializedName("link")
    var link: String? = null,

    @SerializedName("created_at")
    var createdAt: String? = null,

    @SerializedName("updated_at")
    var updatedAt: String? = null
)