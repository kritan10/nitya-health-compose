package com.kritan.nityahealth.base.api

import okio.IOException
import retrofit2.Response

object MyApi {
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