package com.oss.surftesttask_kotlinversion.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostModelNetworkEntity(
    @SerializedName("page")
    @Expose
    var page: Int,

    @SerializedName("results")
    @Expose
    var results: List<ResultsNetworkEntity>,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int,

    @SerializedName("total_results")
    @Expose
    var totalResults: Int
) {}