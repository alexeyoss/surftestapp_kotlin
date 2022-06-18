package com.oss.surftesttask_kotlinversion.utils

sealed class LikeState<out R> {
    data class Success<out R>(val rowsAmount: R) : LikeState<R>()
    data class Error(val exception: Exception) : LikeState<Nothing>()
    object Loading : LikeState<Nothing>()
}