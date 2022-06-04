package com.oss.surftesttask_kotlinversion.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.oss.surftesttask_kotlinversion.databinding.FragmentMovieDetailsBinding
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentMovieDetailsBinding

    // TODO Rebuild the layout file for using the backdrop image instead of posterPath

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        initView()
        return mBinding.root
    }

    private fun initView() = with(mBinding) {
        val result = requireArguments().getSerializable(ARG_MOVIE_KEY) as Results

        Glide.with(root)
            .load(result.posterPath)
            .placeholder(Constants.DEFAULT_PICTURE)
            .centerCrop()
            .into(poster)
        filmName.text = result.title
        filmRate.text = result.popularity.toString()
        overview.text = result.overview

    }

    companion object {
        @JvmStatic
        private val ARG_MOVIE_KEY = "ARG_MOVIE_KEY"

        @JvmStatic
        fun newInstance(result: Results): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            fragment.arguments = Bundle().apply { putSerializable(ARG_MOVIE_KEY, result) }
            return fragment
        }
    }
}
