package com.oss.surftesttask_kotlinversion.utils

import android.os.Looper
import android.view.View
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.core.widget.doOnTextChanged
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

private fun checkMainThread() {
    check(Looper.myLooper() == Looper.getMainLooper()) {
        "Expected to be called on the main thread but was " + Thread.currentThread().name
    }
}

@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        checkMainThread()

        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}

@CheckResult
fun SwipeRefreshLayout.refreshes(): Flow<Unit> {
    return callbackFlow {
        checkMainThread()

        setOnRefreshListener { trySend(Unit) }
        awaitClose { setOnRefreshListener(null) }
    }
}

@CheckResult
fun View.clicks(): Flow<View> {
    return callbackFlow {
        checkMainThread()

        setOnClickListener { trySend(it) }
        awaitClose { setOnClickListener(null) }
    }
}
