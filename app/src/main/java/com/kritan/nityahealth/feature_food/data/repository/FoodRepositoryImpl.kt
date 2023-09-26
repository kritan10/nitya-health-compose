package com.kritan.nityahealth.feature_food.data.repository

import com.kritan.nityahealth.base.api.ApiResponse
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_food.data.models.Food
import com.kritan.nityahealth.feature_food.data.models.FoodDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodRepositoryImpl(private val httpClient: HttpClient) : FoodRepository {
    override suspend fun getAllFood(): Flow<Resource<List<Food>>> {
        return flow {
            emit(Resource.Loading(true))
            val foodList: List<Food>? = try {
                val response = httpClient.get("https://health.sajiloweb.com/api/food")
                response.body<ApiResponse<FoodDTO>>().data?.food
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            emit(Resource.Success(foodList, "success"))
            emit(Resource.Loading(false))
        }
    }
}