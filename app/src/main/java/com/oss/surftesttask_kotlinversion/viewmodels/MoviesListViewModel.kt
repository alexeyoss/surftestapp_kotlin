package com.oss.surftesttask_kotlinversion.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.surftesttask_kotlinversion.models.Result
import com.oss.surftesttask_kotlinversion.repositories.Repository
import kotlinx.coroutines.*

class MoviesListViewModel : ViewModel() {

    private var mActualData: MutableLiveData<MutableList<Result>> = MutableLiveData()
    private lateinit var itemSelected: MutableList<Result>
    private var errorMessage = MutableLiveData<String>()
    private var loading = MutableLiveData<Boolean>()
    private var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    init {
        getMovies()
    }

    fun getMovies() {
        setLoading(true)
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response =
                Repository.getMovies() // TODO handle the Exception without INTERNET connection
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

    fun getSearchMovies(query: String) {
        setLoading(true)
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response =
                Repository.getSearchMovies(query) // TODO handle the Exception without INTERNET connection
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

    fun getLastData(): LiveData<MutableList<Result>> = mActualData
    fun getErrorMessage(): LiveData<String> = errorMessage
    fun getLoading(): LiveData<Boolean> = loading


    // For MovieDetails fragment
    fun putItemSelected(item: MutableList<Result>) {
        itemSelected = item
    }

    fun getItemSelected(): MutableList<Result> = itemSelected

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