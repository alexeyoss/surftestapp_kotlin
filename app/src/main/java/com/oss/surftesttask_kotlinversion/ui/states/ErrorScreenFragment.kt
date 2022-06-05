package com.oss.surftesttask_kotlinversion.ui.states

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.contract.navigator
import com.oss.surftesttask_kotlinversion.databinding.FragmentErrorScreenBinding
import com.oss.surftesttask_kotlinversion.ui.movies_list.MoviesListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorScreenFragment : Fragment() {

    private lateinit var mBinding: FragmentErrorScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentErrorScreenBinding.inflate(inflater, container, false)

        initListeners()
        return mBinding.root
    }

    fun initListeners() = with(mBinding) {
        if (!refreshImageButton.hasOnClickListeners()) {
            refreshImageButton.setOnClickListener {
                navigator().launch(MoviesListFragment::class.java, null)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): ErrorScreenFragment = ErrorScreenFragment()
    }
}