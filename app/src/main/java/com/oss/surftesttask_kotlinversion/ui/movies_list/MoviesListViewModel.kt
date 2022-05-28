package com.oss.surftesttask_kotlinversion.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.surftesttask_kotlinversion.data.repository.Repository
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MoviesListViewModel
@Inject
constructor(
    @Named("Repository") private val repo: Repository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Results>>> = MutableLiveData()
    val dateState: LiveData<DataState<List<Results>>> get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (mainStateEvent) {
                is MainStateEvent.GetResultEvent -> {
                    repo.getMovies()
                        .onEach { dateState ->
                            _dataState.value = dateState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.SearchResultEvent -> {

                }

                is MainStateEvent.ErrorEvent -> {

                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetResultEvent : MainStateEvent()
    object SearchResultEvent : MainStateEvent()
    object ErrorEvent : MainStateEvent()

}

