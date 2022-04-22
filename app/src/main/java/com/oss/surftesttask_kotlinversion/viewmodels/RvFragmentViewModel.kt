package com.oss.surftesttask_kotlinversion.viewmodels

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oss.surftesttask_kotlinversion.models.Result
import com.oss.surftesttask_kotlinversion.repositories.Repository
import kotlinx.coroutines.*

class RvFragmentViewModel() : ViewModel() {

    private var mActualData: MutableLiveData<MutableList<Result>> = MutableLiveData()
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
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
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
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
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

//    companion object {
//        private lateinit var instance: RvFragmentViewModel
//
//        @MainThread
//        fun getInstance(modelId: Long): RvFragmentViewModel {
//            instance = if (::instance.isInitialized) instance else RvFragmentViewModel(modelId)
//            return instance
//        }
//    }
}