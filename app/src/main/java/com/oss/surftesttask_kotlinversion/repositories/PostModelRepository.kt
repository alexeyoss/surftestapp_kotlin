package com.oss.surftesttask_kotlinversion.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.oss.surftesttask_kotlinversion.models.PostModel
import com.oss.surftesttask_kotlinversion.models.Result
import com.oss.surftesttask_kotlinversion.network.ApiService
import com.oss.surftesttask_kotlinversion.network.Client
import com.oss.surftesttask_kotlinversion.support.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PostModelRepository {
    private val api: ApiService = Client().getApiService()
    private val data = MutableLiveData<MutableList<Result>>()

    fun getData(): MutableLiveData<MutableList<Result>> {
        api.getData(
            Constants.API_VERSION,
            Constants.API_KEY,
            Constants.DEFAULT_LANGUAGE,
            Constants.DEFAULT_SORT_BY,
            Constants.DEFAULT_INCLUDE_ADULT,
            Constants.DEFAULT_INCLUDE_VIDEO,
            Constants.WITH_WATCH_MONETIZATION_TYPES
        ).enqueue(object : Callback<PostModel?> {
            override fun onResponse(call: Call<PostModel?>, response: Response<PostModel?>) {
                assert(response.body() != null)
                data.value = response.body()?.results as MutableList<Result>
            }

            override fun onFailure(call: Call<PostModel?>, t: Throwable) {
                Log.i("onFailure", t.message.toString())
                throw t
            }

        })
        return data
    }


    fun getSearchData(query: String): MutableLiveData<MutableList<Result>> {
        api.getSearchData(
            Constants.API_VERSION,
            Constants.API_KEY,
            query
        ).enqueue(object : Callback<PostModel?> {
            override fun onResponse(call: Call<PostModel?>, response: Response<PostModel?>) {
                assert(response.body() != null)
                data.value = response.body()?.results as MutableList<Result>
            }

            override fun onFailure(call: Call<PostModel?>, t: Throwable) {
                Log.i("onFailure", t.message.toString())
                throw t
            }
        })
        return data
    }


}