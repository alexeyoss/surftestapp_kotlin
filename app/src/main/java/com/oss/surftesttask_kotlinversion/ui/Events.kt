package com.oss.surftesttask_kotlinversion.ui


sealed class Events<out R> {
    object GetResultEvent : Events<Any?>()
    data class SearchResultEvent<R>(val query: R) : Events<R>()
    object ErrorEvent : Events<Any?>()
    data class LikeMovie<R>(val movieId: Int, val isLiked: Boolean) : Events<R>()
}
