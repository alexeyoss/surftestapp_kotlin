package com.oss.surftesttask_kotlinversion.ui.movies_list_screen

import com.oss.surftesttask_kotlinversion.models.Results

interface AdapterOnClickListener {
    fun onItemClicked(result: Results)
    fun onLikeClicked(movieId: Int)
}