package com.oss.surftesttask_kotlinversion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class ViewModelFactory(private val modelID: Long) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RvFragmentViewModel(modelID) as T
    }
}