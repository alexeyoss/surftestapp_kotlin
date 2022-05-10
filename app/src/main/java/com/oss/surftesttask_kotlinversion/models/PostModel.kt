package com.oss.surftesttask_kotlinversion.models

import com.oss.surftesttask_kotlinversion.retrofit.ResultsNetworkEntity

data class PostModel(
    var page: Int,
    var results: List<ResultsNetworkEntity>,
    var totalPages: Int,
    var totalResults: Int
)
