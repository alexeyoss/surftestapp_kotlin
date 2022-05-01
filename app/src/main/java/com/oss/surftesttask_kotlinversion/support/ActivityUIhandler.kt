package com.oss.surftesttask_kotlinversion.support

import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.adapters.RecycleViewAdapter

fun Fragment.handleUI(): ActivityUIhandler = requireActivity() as ActivityUIhandler

interface ActivityUIhandler {
    fun showErrorFragment()

    fun showEmptySearch()

    fun showRecycleViewFragment()
}
