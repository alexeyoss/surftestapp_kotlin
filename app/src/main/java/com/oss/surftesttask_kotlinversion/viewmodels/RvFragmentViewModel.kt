package com.oss.surftesttask_kotlinversion.viewmodels

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oss.surftesttask_kotlinversion.models.Result
import com.oss.surftesttask_kotlinversion.repositories.Repository
import kotlinx.coroutines.*

class RvFragmentViewModel(modelID: Long) : ViewModel() {

    var mActualData: MutableLiveData<MutableList<Result>> = MutableLiveData()
    private var errorMessage = MutableLiveData<String>()
    private var loading = MutableLiveData<Boolean>() //
    private var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun getMovies() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = Repository.getMovies()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    mActualData.postValue(response.body()?.results?.toMutableList())
                } else {
                    onError(response.message()) // Visibility handling
                }
            }
        }
    }

    fun getSearchMovies(query: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = Repository.getSearchMovies(query)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    mActualData.postValue(response.body()?.results?.toMutableList())
                } else {
                    onError(response.message())
                }
            }
        }
    }

    fun getLastData(): MutableLiveData<MutableList<Result>> = mActualData
    fun getErrorMessage(): MutableLiveData<String> = errorMessage
    fun getLoading(): MutableLiveData<Boolean> = loading

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    companion object {
        private lateinit var instance: RvFragmentViewModel

        @MainThread
        fun getInstance(modelId: Long): RvFragmentViewModel {
            instance = if (::instance.isInitialized) instance else RvFragmentViewModel(modelId)
            return instance
        }
    }
}