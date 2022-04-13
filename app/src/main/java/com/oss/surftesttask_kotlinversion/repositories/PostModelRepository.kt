package com.oss.surftesttask_kotlinversion.repositories

import androidx.lifecycle.MutableLiveData
import com.oss.surftesttask_kotlinversion.models.PostModel
import com.oss.surftesttask_kotlinversion.network.ApiService
import com.oss.surftesttask_kotlinversion.network.Client
import com.oss.surftesttask_kotlinversion.support.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostModelRepository {
    private val api: ApiService = Client().getApiService()
    val data by lazy { MutableLiveData<List<com.oss.surftesttask_kotlinversion.models.Result>>() }


    fun getInitList(): MutableLiveData<List<com.oss.surftesttask_kotlinversion.models.Result>> {
        api.getInitData(
            Constants.API_VERSION,
            Constants.API_KEY,
            Constants.DEFAULT_LANGUAGE,
            Constants.DEFAULT_SORT_BY,
            Constants.DEFAULT_INCLUDE_ADULT,
            Constants.DEFAULT_INCLUDE_VIDEO,
            Constants.WITH_WATCH_MONETIZATION_TYPES
        )?.enqueue(object : Callback<PostModel?> {
            override fun onResponse(call: Call<PostModel?>, response: Response<PostModel?>) {
                assert(response.body() != null)
                data.value = response.body()?.results
            }

            override fun onFailure(call: Call<PostModel?>, t: Throwable) {

            }

        })
        return data


//        fun getSearchList(query: String): MutableLiveData<List<com.oss.surftesttask_kotlinversion.models.Result>> {
//            api.searchFilm(
//                Constants.API_VERSION,
//                Constants.API_KEY,
//                query
//            )?.enqueue(object : Callback<PostModel?> {
//                override fun onResponse(call: Call<PostModel?>, response: Response<PostModel?>) {
//                    assert(response.body() != null)
//                    data.value = response.body()?.results
//                }
//
//                override fun onFailure(call: Call<PostModel?>, t: Throwable) {
//
//                }
//            })
//            return data
//        }
    }
}