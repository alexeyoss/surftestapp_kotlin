package com.oss.surftesttask_kotlinversion.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.jakewharton.rxbinding2.widget.RxTextView
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.databinding.ActivityMainBinding
import com.oss.surftesttask_kotlinversion.fragments.RecycleViewFragment
import com.oss.surftesttask_kotlinversion.models.Result
import com.oss.surftesttask_kotlinversion.viewmodels.MainActitivyViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initFields()
        initListeners()
    }

    private fun initListeners() = with(mBinding) {
//        etSearch.doAfterTextChanged {
//            if (it.toString().isNotEmpty()) {
//                MainActitivyViewModel().getData(it.toString())
//            } else {
//                MainActitivyViewModel().getData()
//            }
//        }
        RxTextView.textChanges(etSearch)
            .map { it.toString() }
            .debounce(400, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    private fun initFields() {
        supportFragmentManager.beginTransaction().replace(R.id.dataContainer, RecycleViewFragment())
            .commit()
    }
}