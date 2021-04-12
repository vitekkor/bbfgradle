// Original bug: KT-34924

package com.faztudo.common.domain

sealed class Resource<out T : Any>(open val data: T?) {
    data class Success<out T : Any>(override val data: T) : Resource<T>(data)
    data class Loading<out T : Any>(override val data: T? = null) : Resource<T>(data)
    data class Failure<out T : Any>(override val data: T? = null,
                              val exception: Exception) : Resource<T>(data)

    fun onSuccess(onSuccess: (data: T) -> Unit) = this.also {
        if (this is Success)
            onSuccess(data)
    }

    fun onLoading(onLoading: (data: T?) -> Unit) = this.also {
        if (this is Loading)
            onLoading(data)
    }

    fun onFailure(onFailure: (data: T?, exception: Exception) -> Unit) = this.also {
        if (this is Failure)
            onFailure(data, exception)
    }

    fun <Y : Any> mapResource(transform: (T) -> Y): Resource<Y> = try {
//        logd("mapResource", "Converting $this from type ${X::class.java} to ${Y::class.java}")
        when (this) {
            is Success<T> -> Success<Y>(
                transform(data)
            )
            is Loading<T> -> Loading<Y>(
                data?.let { transform(it) }
            )
            is Failure<T> -> Failure<Y>(
                data?.let { transform(it) },
                exception
            )
        }
    } catch (e: Throwable) {
//        Log.e("mapResource", "Failed to convert $this from type ${X::class.java} to ${Y::class.java}", e)
        Failure<Y>(data?.let { transform(it) }, Exception(e))
    }

}
