package com.oss.surftesttask_kotlinversion.support

import android.text.Editable
import com.oss.surftesttask_kotlinversion.viewmodels.RvFragmentViewModel

fun chooseCall(query: Editable?) {
    if (query.toString().isNotEmpty()) {
        RvFragmentViewModel.getInstance(1).getSearchMovies(query.toString())
    } else {
        RvFragmentViewModel.getInstance(1).getMovies()
    }
}

