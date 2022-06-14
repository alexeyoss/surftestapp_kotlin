package com.oss.surftesttask_kotlinversion.ui.movies_list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.surftesttask_kotlinversion.data.Interactor
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.ui.Events
import com.oss.surftesttask_kotlinversion.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel
@Inject
constructor(
    private val interactor: Interactor
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Results>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Results>>> get() = _dataState

    private val _queryText: MutableLiveData<String> = MutableLiveData()
    val queryText: LiveData<String> get() = _queryText

    init {
        setStateEvent(Events.GetResultEvent)
    }

    fun setStateEvent(mainStateEvent: Events<Any?>) {
        viewModelScope.launch(Dispatchers.IO) {
            when (mainStateEvent) {
                is Events.GetResultEvent -> {
                    interactor.getMoviesFromNetwork()
                        .onEach { dateState ->
                            _dataState.value = dateState
                        }
                        .launchIn(viewModelScope)
                }

                is Events.ErrorEvent -> Unit

                is Events.SearchResultEvent -> {
                    interactor.getMoviesFromNetwork(mainStateEvent.query as String)
                        .onEach { dateState ->
                            _dataState.value = dateState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    fun setQueryText(text: String) {
        _queryText.value = text
    }
}