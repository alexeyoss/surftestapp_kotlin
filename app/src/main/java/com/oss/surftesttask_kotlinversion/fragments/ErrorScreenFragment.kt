package com.oss.surftesttask_kotlinversion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.databinding.FragmentErrorScreenBinding
import com.oss.surftesttask_kotlinversion.support.navigator
import com.oss.surftesttask_kotlinversion.support.replaceFragment

class ErrorScreenFragment : Fragment() {

    private lateinit var mBinding: FragmentErrorScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentErrorScreenBinding.inflate(inflater, container, false)

        if (!mBinding.refreshImageButton.hasOnClickListeners()) {
            mBinding.refreshImageButton.setOnClickListener {
                navigator().showRecycleViewFragment()
            }
        }
        return mBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(): ErrorScreenFragment = ErrorScreenFragment()
    }

}