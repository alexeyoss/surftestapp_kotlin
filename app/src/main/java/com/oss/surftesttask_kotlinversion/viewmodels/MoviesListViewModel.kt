package com.oss.surftesttask_kotlinversion.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.surftesttask_kotlinversion.retrofit.ResultsNetworkEntity
import com.oss.surftesttask_kotlinversion.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel
@Inject
constructor(private val repo: Repository) : ViewModel() {

    private var mActualData: MutableLiveData<MutableList<ResultsNetworkEntity>> = MutableLiveData()

    private var errorMessage = MutableLiveData<String>()
    private var loading = MutableLiveData<Boolean>()
    private var job: Job? = null

    val getActualData: LiveData<MutableList<ResultsNetworkEntity>> get() = mActualData
    val getErrorInfo: LiveData<String> get() = errorMessage
    val getLoadingStatus: LiveData<Boolean> get() = loading

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    init {
        callGetMovies()
    }

    fun callGetMovies() {
        setLoading(true)
        job = viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getMovies()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    mActualData.postValue(response.body()?.results?.toMutableList())
                } else {
                    onError(response.message())
                }
                setLoading(false)
            }
        }
    }

    fun callSearchMovies(query: String) {
        setLoading(true)
        job = viewModelScope.launch(Dispatchers.IO) {
            val response =
                repo.getSearchMovies(query) // TODO handle the Exception without INTERNET connection
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    mActualData.postValue(response.body()?.results?.toMutableList())
                    setLoading(false)
                } else {
                    onError(response.message())
                }
            }
        }
    }

    fun getCertainData(position: Int): ResultsNetworkEntity = mActualData.value?.get(position) ?: ResultsNetworkEntity()

    private fun onError(message: String) {
        errorMessage.value = message
        setLoading(false)
    }

    private fun setLoading(value: Boolean) {
        loading.value = value
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
