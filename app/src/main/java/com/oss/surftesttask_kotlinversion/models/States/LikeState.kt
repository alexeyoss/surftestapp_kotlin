package com.oss.surftesttask_kotlinversion.models.States

sealed class LikeState<out R> {
    object Success : LikeState<Nothing>()
    data class Error(val exception: Exception) : LikeState<Nothing>()
    object Loading : LikeState<Nothing>()
}