package com.oss.surftesttask_kotlinversion.utils

import androidx.fragment.app.Fragment

fun Fragment.handleUI(): ActivityUIhandler = requireActivity() as ActivityUIhandler

interface ActivityUIhandler {
    fun showErrorFragment()

    fun showEmptySearch()

    fun showRecycleViewFragment()

    fun hideSearchBar(status: Int)
}