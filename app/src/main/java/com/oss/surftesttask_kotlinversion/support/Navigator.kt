package com.oss.surftesttask_kotlinversion.support

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator = requireActivity() as Navigator

interface Navigator {
    fun showErrorFragment()

    fun showEmptySearch()

    fun showRecycleViewFragment()

}