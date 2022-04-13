package com.oss.surftesttask_kotlinversion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oss.surftesttask_kotlinversion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initFields()
        initFun()
    }

    private fun initFun() {}

    private fun initFields() {

    }
}