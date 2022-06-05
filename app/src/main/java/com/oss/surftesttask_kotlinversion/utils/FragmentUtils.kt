package com.oss.surftesttask_kotlinversion.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.models.Results

interface AdapterOnClickListener {
    fun onItemClicked(result: Results)
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


