package com.oss.surftesttask_kotlinversion.navigator

import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.ui.base.BaseScreen

fun Fragment.navigate(): Navigator = requireActivity() as Navigator

interface Navigator {

    fun launch(screen: BaseScreen, addStack: Boolean = false)

    fun showErrorFragment()

    fun showEmptySearch()

    fun showRecycleViewFragment()

    fun showSearchBar(visible: Boolean)
}