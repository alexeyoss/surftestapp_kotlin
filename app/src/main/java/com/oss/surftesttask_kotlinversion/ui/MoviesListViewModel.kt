package com.oss.surftesttask_kotlinversion.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.repositories.Repository
import com.oss.surftesttask_kotlinversion.utils.DataState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewModelScoped
class MoviesListViewModel
@Inject
constructor(
    private val repo: Repository,
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
//                    repo.searchData()
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

//    private var mActualData: MutableLiveData<MutableList<ResultsNetworkEntity>> = MutableLiveData()
//
//    private var errorMessage = MutableLiveData<String>()
//    private var loading = MutableLiveData<Boolean>()
//    private var job: Job? = null
//
//    val getActualData: LiveData<MutableList<ResultsNetworkEntity>> get() = mActualData
//    val getErrorInfo: LiveData<String> get() = errorMessage
//    val getLoadingStatus: LiveData<Boolean> get() = loading
//
//    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        onError("Exception handled: ${throwable.localizedMessage}")
//    }
//
//    init {
//        callGetMovies()
//    }
//
//    fun callGetMovies() {
//        setLoading(true)
//        job = viewModelScope.launch(Dispatchers.IO) {
//            val response = repo.getMovies()
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    mActualData.postValue(response.body()?.results?.toMutableList())
//                } else {
//                    onError(response.message())
//                }
//                setLoading(false)
//            }
//        }
//    }
//
//    fun callSearchMovies(query: String) {
//        setLoading(true)
//        job = viewModelScope.launch(Dispatchers.IO) {
//            val response =
//                repo.getSearchMovies(query) // TODO handle the Exception without INTERNET connection
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    mActualData.postValue(response.body()?.results?.toMutableList())
//                    setLoading(false)
//                } else {
//                    onError(response.message())
//                }
//            }
//        }
//    }
//
////    fun getCertainData(position: Int): ResultsNetworkEntity = mActualData.value?.get(position) ?: ResultsNetworkEntity()
//
//    private fun onError(message: String) {
//        errorMessage.value = message
//        setLoading(false)
//    }
//
//    private fun setLoading(value: Boolean) {
//        loading.value = value
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }


