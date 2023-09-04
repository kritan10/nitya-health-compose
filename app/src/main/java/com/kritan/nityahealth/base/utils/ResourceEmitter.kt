package com.kritan.nityahealth.base.utils

import kotlinx.coroutines.flow.FlowCollector

suspend fun <T> FlowCollector<Resource<T>>.emitDataOrNull(data: T?) {
    if (data != null) {
        emit(Resource.Success(data = data))
    } else {
        emit(Resource.Error("Couldn't load data"))
    }
}