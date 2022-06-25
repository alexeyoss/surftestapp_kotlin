package com.oss.surftesttask_kotlinversion.models.States

sealed class LikeState<out R> {
    data class Success(val data: Boolean) : LikeState<Boolean>()
    data class Error(val exception: Exception) : LikeState<Nothing>()
    object Loading : LikeState<Nothing>()
}