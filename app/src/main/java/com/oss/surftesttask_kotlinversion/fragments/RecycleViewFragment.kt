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
import com.oss.surftesttask_kotlinversion.viewmodels.RecycleViewFragmentViewModel

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
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mAdapter
        recyclerView.setHasFixedSize(true)
    }

    private fun initViewModel() {
        val model =
            ViewModelProvider(activity as MainActivity)[RecycleViewFragmentViewModel::class.java]

        model.getData().observe(
            viewLifecycleOwner
        ) { mAdapter.setData(it) }
    }
}