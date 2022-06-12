package com.oss.surftesttask_kotlinversion.contract

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import java.io.Serializable

typealias  ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator = requireActivity() as Navigator

interface Navigator {

    fun launchScreen(screen: Fragment)

    fun goBack()

    fun showSnackBar()

    fun <T : Serializable> publishResult(result: T)

    fun <T : Serializable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    )
}