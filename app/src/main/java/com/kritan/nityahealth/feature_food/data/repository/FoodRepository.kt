package com.kritan.nityahealth.feature_food.data.repository

import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_food.data.models.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    suspend fun getAllFood(): Flow<Resource<List<Food>>>

}