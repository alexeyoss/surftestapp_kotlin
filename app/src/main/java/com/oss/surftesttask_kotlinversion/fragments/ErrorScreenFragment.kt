package com.oss.surftesttask_kotlinversion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.databinding.FragmentErrorScreenBinding

class ErrorScreenFragment : Fragment() {

    private lateinit var mBindging: FragmentErrorScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBindging = FragmentErrorScreenBinding.inflate(inflater, container, false)
        return mBindging.root
    }
}