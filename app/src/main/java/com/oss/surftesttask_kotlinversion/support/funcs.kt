package com.oss.surftesttask_kotlinversion.support

import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.viewmodels.RvFragmentViewModel

fun chooseCall(query: Editable?) {
    if (query.toString().isNotEmpty()) {
        RvFragmentViewModel.getInstance(1).getSearchMovies(query.toString())
    } else {
        RvFragmentViewModel.getInstance(1).getMovies()
    }
}

fun Fragment.replaceFragment(fragment: Fragment, addStack: Boolean = false) {
    if (addStack) {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.dataContainer, fragment)
            .commit()
    } else {
        parentFragmentManager.beginTransaction()
            .replace(R.id.dataContainer, fragment)
            .commit()
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, addStack: Boolean = false) {
    if (addStack) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.dataContainer, fragment)
            .commit()
    } else {
        supportFragmentManager.beginTransaction()
            .replace(R.id.dataContainer, fragment)
            .commit()
    }
}

