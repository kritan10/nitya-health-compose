package com.kritan.nityahealth.base.api

import androidx.annotation.Keep
import okio.IOException
import retrofit2.Response

/**
 * A singleton class that contains generic methods for interacting with the API.
 */
@Keep
object MyApi {
    /**
     * A generic method to make API calls.
     *
     * @param callback a method that yields a [Response] of type [T]
     * @return response data [T] if the call was successful otherwise `null`
     */
    @Keep
    suspend fun <T> fetchFromRemote(callback: suspend () -> Response<ApiResponse<T>>): ApiResponse<T>? {
        return try {
            callback().body()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}