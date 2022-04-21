package com.oss.surftesttask_kotlinversion.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostModel {
    @SerializedName("page")
    @Expose
    var page = 0

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages = 0

    @SerializedName("total_results")
    @Expose
    var totalResults = 0
}
