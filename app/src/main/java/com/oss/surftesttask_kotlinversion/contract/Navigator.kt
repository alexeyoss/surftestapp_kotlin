package com.oss.surftesttask_kotlinversion.contract

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import java.io.Serializable

typealias  ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator = requireActivity() as Navigator

interface Navigator {

    fun <B,T> launch(screen: Class<B>?, args: T?)

    fun goBack()

    fun showSearchContainer(visible: Boolean)

//    fun <T : Serializable> listenResult(
//        clazz: Class<T>,
//        owner: LifecycleOwner,
//        listener: ResultListener<T>
//    )
}