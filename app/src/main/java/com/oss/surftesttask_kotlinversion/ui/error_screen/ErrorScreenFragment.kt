package com.oss.surftesttask_kotlinversion.ui.error_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.oss.surftesttask_kotlinversion.contract.navigator
import com.oss.surftesttask_kotlinversion.databinding.FragmentErrorScreenBinding
import com.oss.surftesttask_kotlinversion.ui.movies_list_screen.MoviesListFragment
import com.oss.surftesttask_kotlinversion.utils.clicks
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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

    private fun initListeners() = with(mBinding) {
        refreshImageButton.clicks()
            .onEach { navigator().launchScreen(MoviesListFragment()) }
            .launchIn(lifecycleScope)
    }
}