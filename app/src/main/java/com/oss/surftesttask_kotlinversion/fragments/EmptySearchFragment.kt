package com.oss.surftesttask_kotlinversion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.databinding.FragmentEmptySearchBinding

class EmptySearchFragment : Fragment() {

    private lateinit var mBinding: FragmentEmptySearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentEmptySearchBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}