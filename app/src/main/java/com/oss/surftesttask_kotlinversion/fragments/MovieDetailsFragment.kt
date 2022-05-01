package com.oss.surftesttask_kotlinversion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.databinding.FragmentMovieDetailsBinding
import com.oss.surftesttask_kotlinversion.models.Result

class MovieDetailsFragment(movieData: MutableList<Result>) : Fragment() {

    private lateinit var mBinding: FragmentMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    private fun getMovieData(): Any? = requireArguments().get(MOVIE_ID)

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"

        fun newInstance(movieData: MutableList<Result>): MovieDetailsFragment {
            val fragment = MovieDetailsFragment(movieData)
            fragment.arguments = bundleOf(MOVIE_ID to movieData)
            return fragment
        }
    }
}
