package com.oss.surftesttask_kotlinversion.network

import android.app.Application
import com.oss.surftesttask_kotlinversion.support.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Client : Application() {

    companion object {

        private fun getHTTPClientInsatnce(): OkHttpClient {
            return OkHttpClient.Builder()
                .callTimeout(15, TimeUnit.SECONDS)
                .build()
        }

        private fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getHTTPClientInsatnce())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getApiService(): ApiService {
            return getRetrofitInstance().create(ApiService::class.java)
        }

    }
}