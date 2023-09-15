package com.kritan.nityahealth.auth.data.repository

import android.util.Log
import com.kritan.nityahealth.auth.data.api.FacebookApi
import com.kritan.nityahealth.auth.data.models.FacebookUserData
import com.kritan.nityahealth.base.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class FacebookRepository @Inject constructor(
    private val api: FacebookApi
) {
    suspend fun getUserName(accessToken: String): String? {
        return try {
            val name = api.getUserName(accessToken).body()?.name
            Log.d("FB", name ?: "null")
            name
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getUserData(
        accessToken: String,
        userId: String
    ): Flow<Resource<FacebookUserData?>> {
        return flow {
            emit(Resource.Loading(true))

            val userData = try {
                val userData = api.getUserData(accessToken = accessToken, userId = userId).body()
                Log.d("FB", userData.toString())
                userData
            } catch (e: IOException) {
                e.printStackTrace()
                null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

            if (userData == null) {
                emit(Resource.Error("Failed to load user data."))
            } else {
                emit(Resource.Success(userData, "Success"))
            }

            emit(Resource.Loading(false))
        }
    }
}