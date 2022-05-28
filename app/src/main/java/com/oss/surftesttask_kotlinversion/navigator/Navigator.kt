package com.oss.surftesttask_kotlinversion.navigator

import androidx.fragment.app.Fragment

fun Fragment.navigate(): Navigator = requireActivity() as Navigator

interface Navigator {
    fun showErrorFragment()

    fun showEmptySearch()

    fun showRecycleViewFragment()

    fun hideSearchBar(status: Int)
}