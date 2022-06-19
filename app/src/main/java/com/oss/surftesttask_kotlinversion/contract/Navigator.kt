package com.oss.surftesttask_kotlinversion.contract

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

typealias  ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator = requireActivity() as Navigator

interface Navigator : LikeHandler {

    fun launchScreen(screen: Fragment)

    fun goBack()

    fun showSnackBar()

    override fun likeOperationMade()

    fun <T : Parcelable> publishResult(result: T)

    fun <T : Parcelable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    )
}