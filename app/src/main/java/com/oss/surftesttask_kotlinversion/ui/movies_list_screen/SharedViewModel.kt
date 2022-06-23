package com.oss.surftesttask_kotlinversion.ui.movies_list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.surftesttask_kotlinversion.data.Interactor
import com.oss.surftesttask_kotlinversion.di.IoDispatchers
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.models.States.DataState
import com.oss.surftesttask_kotlinversion.models.States.LikeState
import com.oss.surftesttask_kotlinversion.ui.Events
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel
@Inject
constructor(
    private val interactor: Interactor,
    @IoDispatchers
    private val IoDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Results>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Results>>> get() = _dataState

    private val _likeState: MutableLiveData<LikeState<Int>> = MutableLiveData()
    val likeState: LiveData<LikeState<Int>> get() = _likeState

    private val _queryText: MutableLiveData<String> = MutableLiveData()
    val queryText: LiveData<String> get() = _queryText

    init {
        setStateEvent(Events.GetResultEvent)
    }

    fun setStateEvent(mainStateEvent: Events<Any?>) {
        viewModelScope.launch(IoDispatcher) {
            when (mainStateEvent) {
                is Events.GetResultEvent -> {
                    interactor.getCachedMoviesFromDb()
                        .onEach { item ->
                            _dataState.value = item
                        }
                        .launchIn(viewModelScope)
                }

                is Events.ErrorEvent -> Unit

                is Events.SearchResultEvent -> {
                    interactor.getCachedMoviesFromDb(mainStateEvent.query as String)
                        .onEach { item ->
                            _dataState.value = item
                        }
                        .launchIn(viewModelScope)
                }

                is Events.LikeMovie -> {
                    interactor.setLikedMovieStatus(mainStateEvent.movieId, mainStateEvent.isLiked)
                        .onEach { item ->
                            _likeState.value = item
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