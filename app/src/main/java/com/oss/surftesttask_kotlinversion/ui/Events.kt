package com.oss.surftesttask_kotlinversion.ui


sealed class Events {
    object GetResultEvent : Events()
    object OnRestoreEvent : Events()
    object ErrorEvent : Events()
}
