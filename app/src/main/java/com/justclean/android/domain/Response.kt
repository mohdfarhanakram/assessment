package com.justclean.android.domain

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */
class Response<Any> {
    var status: Status
        private set
    var data: Any? = null
        private set
    var error: Throwable? = null
        private set

    constructor() {
        status = Status.LOADING
    }

    private constructor(status: Status, data: Any, error: Throwable?) {
        this.status = status
        this.data = data
        this.error = error
    }

    fun loading(): Response<Any> {
        status = Status.LOADING
        return this
    }

    fun setData(data: Any): Response<Any> {
        this.data = data
        status = Status.SUCCESS
        return this
    }

    fun setError(error: Throwable?): Response<Any> {
        status = Status.ERROR
        this.error = error
        return this
    }

    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <Any> stateLoading(): Response<Any?> {
            return Response<Any?>(Status.LOADING, null, null)
        }

        fun <Any> stateSuccess(data: Any): Response<Any> {
            return Response(Status.SUCCESS, data, null)
        }

        fun <Any> stateError(throwable: Throwable?): Response<Any?> {
            return Response<Any?>(Status.ERROR, null, throwable)
        }

        fun <Any> stateLoadingWithLocal(data: Any): Response<Any> {
            return Response(Status.LOADING, data, null)
        }
    }
}