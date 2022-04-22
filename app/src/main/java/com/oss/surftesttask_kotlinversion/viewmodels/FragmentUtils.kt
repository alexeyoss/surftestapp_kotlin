package com.oss.surftesttask_kotlinversion.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oss.surftesttask_kotlinversion.App

open class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            RvFragmentViewModel::class.java -> {
                RvFragmentViewModel()
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}


fun Fragment.factory(): ViewModelFactory =
    ViewModelFactory(requireContext().applicationContext as App)