package com.oss.surftesttask_kotlinversion.navigator

import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.ui.base.BaseScreen

fun Fragment.navigate(): Navigator = requireActivity() as Navigator

interface Navigator {

    fun launch(screen: BaseScreen)

    fun showErrorFragment()

    fun showEmptySearch()

    fun showRecycleViewFragment()

    fun hideSearchBar(status: Int)
}