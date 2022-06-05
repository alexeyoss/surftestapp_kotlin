package com.oss.surftesttask_kotlinversion.contract

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator = requireActivity() as Navigator

interface Navigator {

    fun <B, T> launch(screen: Class<B>?, args: T?)

    fun goBack()

    fun showSearchContainer(visible: Boolean)
}