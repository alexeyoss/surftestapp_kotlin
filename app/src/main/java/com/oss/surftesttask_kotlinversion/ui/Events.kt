package com.oss.surftesttask_kotlinversion.ui


sealed class Events<out R> {
    object GetResultEvent : Events<Any?>()
    data class SearchResultEvent<R>(val query: R) : Events<R>()
    object ErrorEvent : Events<Any?>()
}
