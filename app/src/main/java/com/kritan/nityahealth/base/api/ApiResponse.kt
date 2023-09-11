package com.kritan.nityahealth.base.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Generic class for holding API response.
 *
 * @property success `true` indicates whether the API call was successful and `false` indicates a failure
 * @property data the response data returned from the API call
 * @property message additional messages provided by the server
 *
 *
 */
@Keep
data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("data")
    val data: T?,

    @SerializedName("message")
    val message: String
)