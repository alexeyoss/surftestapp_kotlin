package com.oss.surftesttask_kotlinversion.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oss.surftesttask_kotlinversion.data.repository.Repository
import com.oss.surftesttask_kotlinversion.viewmodels.MoviesListViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory
@Inject
constructor(
    private val repo: Repository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            MoviesListViewModel::class.java -> {
                MoviesListViewModel(repo)
            }
            else -> {
                throw IllegalArgumentException("Cannot create instance of the ViewModel")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(Repository())