package com.oss.surftesttask_kotlinversion.support

import androidx.fragment.app.Fragment

fun Fragment.handleUI(): UiHandler = requireActivity() as UiHandler

interface UiHandler {
    fun showErrorFragment()

    fun showEmptySearch()

    fun showRecycleViewFragment()

}