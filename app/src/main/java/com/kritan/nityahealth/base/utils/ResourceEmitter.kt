package com.kritan.nityahealth.base.utils

import androidx.annotation.Keep
import kotlinx.coroutines.flow.FlowCollector

/**
 * A helper [FlowCollector] method that takes data of type [T] and emits
 * a [Resource.Success] containing data if the supplied parameter [data] is not null and a [Resource.Error] otherwise.
 *
 * @param data generic data type to be emitted from the flow
 * @see [Resource]
 * @see [FlowCollector]
 */
@Keep
suspend fun <T> FlowCollector<Resource<T>>.emitDataOrNull(data: T?, message: String?) {
    if (data != null) {
        emit(Resource.Success(data = data, message = message ?: "Success"))
    } else {
        emit(Resource.Error(message ?: "Couldn't load data"))
    }
}