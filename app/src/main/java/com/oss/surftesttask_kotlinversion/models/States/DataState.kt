package com.oss.surftesttask_kotlinversion.models.States

sealed class DataState<out R> {
    data class Success<out R>(val data: R) : DataState<R>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    data class Loading<out R>(val data: R) : DataState<R>()
}