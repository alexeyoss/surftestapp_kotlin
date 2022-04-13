package com.oss.surftesttask_kotlinversion.viewmodels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oss.surftesttask_kotlinversion.models.Result
import com.oss.surftesttask_kotlinversion.repositories.PostModelRepository

class RecycleViewFragmentViewModel : ViewModel(),
    DefaultLifecycleObserver {

    private lateinit var mActualData: LiveData<MutableList<Result>>
    private var mRepository: PostModelRepository = PostModelRepository


    fun getData(): LiveData<MutableList<Result>> {
        mActualData = mRepository.getData()
        return mActualData
    }

    fun getData(query: String): LiveData<MutableList<Result>> {
        mActualData = mRepository.getSearchData(query)
        return mActualData
    }

    override fun onCleared() {
        super.onCleared()
        // TODO
    }
}