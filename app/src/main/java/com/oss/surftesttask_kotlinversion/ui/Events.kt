package com.oss.surftesttask_kotlinversion.ui


sealed class Events {
    object GetResultEvent : Events()
    data class SearchResultEvent(val searchText: String) : Events() // TODO
    object OnRestoreEvent : Events()
    object ErrorEvent : Events()
}
