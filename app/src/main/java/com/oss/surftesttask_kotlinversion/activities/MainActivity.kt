package com.oss.surftesttask_kotlinversion.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.databinding.ActivityMainBinding
import com.oss.surftesttask_kotlinversion.fragments.RecycleViewFragment
import com.oss.surftesttask_kotlinversion.support.chooseCall

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
        etSearch.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                chooseCall(s)
            }
        })
//        etSearch.doAfterTextChanged { chooseCall(it) } // debounce and etc.
    }


    private fun initFields() {
        supportFragmentManager.beginTransaction().replace(R.id.dataContainer, RecycleViewFragment())
            .commit()
    }
}