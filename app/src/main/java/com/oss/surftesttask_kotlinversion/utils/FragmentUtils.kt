package com.oss.surftesttask_kotlinversion.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.models.Results

interface AdapterOnClickListener {
    fun onItemClicked(result: Results)
}

fun Fragment.replaceFragmentDataContainer(fragment: Fragment, addStack: Boolean = false) {
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

fun AppCompatActivity.replaceFragmentDataContainer(fragment: Fragment, addStack: Boolean = false) {
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


fun Fragment.replaceFragmentSearchContainer(fragment: Fragment, addStack: Boolean = false) {
    if (addStack) {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.searchContainer, fragment)
            .commit()
    } else {
        parentFragmentManager.beginTransaction()
            .replace(R.id.searchContainer, fragment)
            .commit()
    }
}

fun AppCompatActivity.replaceFragmentSearchContainer(
    fragment: Fragment,
    addStack: Boolean = false
) {
    if (addStack) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.searchContainer, fragment)
            .commit()
    } else {
        supportFragmentManager.beginTransaction()
            .replace(R.id.searchContainer, fragment)
            .commit()
    }
}

