package com.oss.surftesttask_kotlinversion.ui.custom_snackbar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.ContentViewCallback
import com.oss.surftesttask_kotlinversion.R

class CustomSnackBarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defaultStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defaultStyle), ContentViewCallback {

    var title: TextView

    init {
        View.inflate(context, R.layout.view_custom_snackbar, this)
        clipToPadding = false
        this.title = findViewById(R.id.snackbar_text)
    }

    override fun animateContentIn(delay: Int, duration: Int) {
    }

    override fun animateContentOut(delay: Int, duration: Int) {
    }


}