package com.blueberry.thespace.data

sealed class Result<T> {
    class Loading<T> : Result<T>()
    class Success<T>(var data: T): Result<T>()
    class Failure<T>(var message: String? = "Unknown Error"): Result<T>()
}
