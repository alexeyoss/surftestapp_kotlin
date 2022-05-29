package com.oss.surftesttask_kotlinversion

sealed class Events {
    object GetResultEvent : Events()
    object SearchResultEvent : Events()
    object OnRestoreEvent : Events()
    object ErrorEvent : Events()
}
