package com.vrushank.loginfeaturechallenge.util

sealed class Resources<T>(val data:T ?=null, val error: String?=null) {
    class Success<T>(data:T): Resources<T>(data)
    class Error<T>(error: String,data:T?=null): Resources<T>(data,error)
    class Loading<T>(): Resources<T>()
}