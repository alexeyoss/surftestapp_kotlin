package com.oss.surftesttask_kotlinversion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oss.surftesttask_kotlinversion.activities.MainActivity
import com.oss.surftesttask_kotlinversion.adapters.RecycleViewAdapter
import com.oss.surftesttask_kotlinversion.databinding.FragmentRecycleViewBinding
import com.oss.surftesttask_kotlinversion.support.navigator
import com.oss.surftesttask_kotlinversion.viewmodels.RvFragmentViewModel
import com.oss.surftesttask_kotlinversion.viewmodels.ViewModelFactory

class RecycleViewFragment : Fragment() {
    private lateinit var mBinding: FragmentRecycleViewBinding
    private var mAdapter = RecycleViewAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentRecycleViewBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        initViewModel()
    }

    private fun initRecycleView() = with(mBinding) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun initViewModel() = with(mBinding) {
        val viewModel =
            ViewModelProvider(
                activity as MainActivity,
                ViewModelFactory(1)
            )[RvFragmentViewModel::class.java]

        viewModel.getLastData().observe(viewLifecycleOwner) {
            mAdapter.setData(it)
            roundProgressBar.visibility = View.GONE
        }

        viewModel.getErrorMessage().observe(viewLifecycleOwner) {
            navigator().showErrorFragment()
        }

        viewModel.getLoading().observe(viewLifecycleOwner) {
            if (it) roundProgressBar.visibility = View.VISIBLE else View.GONE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): RecycleViewFragment = RecycleViewFragment()
    }
}