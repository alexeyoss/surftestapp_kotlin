package com.oss.surftesttask_kotlinversion.viewmodels

import androidx.lifecycle.*
import com.oss.surftesttask_kotlinversion.models.Result
import com.oss.surftesttask_kotlinversion.repositories.PostModelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActitivyViewModel : ViewModel(),
    DefaultLifecycleObserver {

    var mActualData: LiveData<MutableList<Result>>
    private var mRepository: PostModelRepository = PostModelRepository

    init {
        mActualData = MutableLiveData()
        getData()
    }

    fun getData(): LiveData<MutableList<Result>> {
        mActualData = mRepository.getData()
        return mActualData
    }

    fun getData(query: String): LiveData<MutableList<Result>> {
        mActualData = mRepository.getSearchData(query)
        return mActualData
    }


}