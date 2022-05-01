package com.oss.surftesttask_kotlinversion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class ViewModelFactory(
    modelID: Long
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            MoviesListViewModel::class.java -> {
                MoviesListViewModel()
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}