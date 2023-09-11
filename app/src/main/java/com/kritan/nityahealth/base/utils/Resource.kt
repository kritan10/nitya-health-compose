package com.kritan.nityahealth.base.utils

import androidx.annotation.Keep
import com.kritan.nityahealth.base.utils.Resource.Error
import com.kritan.nityahealth.base.utils.Resource.Loading
import com.kritan.nityahealth.base.utils.Resource.Success

/**
 * A generic helper class to bridge views and models.
 * It is used while retrieving data from a repository.
 * The response is sent to the composables notifying whether the call was successful or failed.
 * It is recommended to emit the corresponding objects from inside a [kotlinx.coroutines.flow.Flow] to correctly update the UI.
 *
 * It contains three sub-classes as follows:
 *
 * [Success] - notifies that the resource call was successful
 *
 * [Error] - notifies that there was an error while making the call
 *
 * [Loading] - notifies when the call starts and ends
 *
 */
@Keep
sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    /**
     * A sub-class of [Resource] that indicates the resource call was successful.
     *
     * @param data the data returned as the response from the call
     */
    @Keep
    class Success<T>(data: T?, message: String?) : Resource<T>(data, message)


    /**
     * A sub-class of [Resource] that indicates the resource call failed.
     *
     * @param message the error message recommended to display in the UI
     * @param data the error data to provide additional context to the error
     *
     */
    @Keep
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)


    /**
     * A sub-class of [Resource] that indicates the progression of the resource call.
     *
     * - emit a `true` value to notify the start of call
     * - emit a `false` value to notify the end of the call
     *
     * @param isLoading indicates the resource call progress
     *
     */
    @Keep
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>()
}