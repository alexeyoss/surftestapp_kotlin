package com.oss.surftesttask_kotlinversion.retrofit

import android.app.Application
import com.oss.surftesttask_kotlinversion.utils.Constants
import dagger.hilt.android.scopes.ServiceScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ServiceScoped
class Client
@Inject
constructor(
    private val api: ApiService,
    private val baseUrl: String = Constants.BASE_URL
) : Application() {

    fun getApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
//            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api::class.java)
    }

    companion object {
        private fun getHttpClientInstance(): OkHttpClient {
            return OkHttpClient.Builder()
                .callTimeout(5, TimeUnit.SECONDS)
                .build()
        }
    }
}