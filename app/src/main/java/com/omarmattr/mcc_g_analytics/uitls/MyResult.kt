package com.omarmattr.mcc_g_analytics.uitls

import androidx.annotation.Nullable


class MyResult<T> private constructor(val status: Status, val message: String?, val data: T) {
    enum class Status {
        SUCCESS, ERROR, LOADING, EMPTY
    }

    companion object {
        fun <T> success(data: T): MyResult<T> {
            return MyResult(Status.SUCCESS, null, data)
        }

        fun <T> success(data: T, message: String?): MyResult<T> {
            return MyResult(Status.SUCCESS, message, data)
        }

        fun <T> error(msg: String?, @Nullable data: T): MyResult<T> {
            return MyResult(Status.ERROR, msg, data)
        }

        fun <T> loading(@Nullable data: T): MyResult<T> {
            return MyResult(Status.LOADING, null, data)
        }

        fun <T> empty(@Nullable data: T): MyResult<T> {
            return MyResult(Status.EMPTY, null, data)
        }
    }
}