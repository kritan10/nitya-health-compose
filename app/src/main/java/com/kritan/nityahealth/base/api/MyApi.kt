package com.kritan.nityahealth.base.api

import okio.IOException
import retrofit2.Response

/**
 * A singleton class that contains generic methods for interacting with the API.
 */
object MyApi {
    /**
     * A generic method to make API calls.
     *
     * @param callback a method that yields a [Response] of type [T]
     * @return response data [T] if the call was successful otherwise `null`
     */
    suspend fun <T> fetchFromRemote(callback: suspend () -> Response<ApiResponse<T>>): T? {
        return try {
            callback().body()?.data
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}