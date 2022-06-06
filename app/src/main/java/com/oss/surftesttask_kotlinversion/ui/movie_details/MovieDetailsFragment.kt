package com.oss.surftesttask_kotlinversion.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.oss.surftesttask_kotlinversion.contract.navigator
import com.oss.surftesttask_kotlinversion.databinding.FragmentMovieDetailsBinding
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        initViews()
        initListeners()

        return mBinding.root
    }

    private fun initViews() = with(mBinding) {
//        navigator().listenResult(Results::class.java, viewLifecycleOwner){
//            result = it
//        }
        val result = requireArguments().getSerializable(ARG_MOVIE_KEY) as Results

        // TODO make animation between placeholder image and poster
        Glide.with(root)
            .load(result.backdropPath)
            .placeholder(Constants.DEFAULT_PICTURE)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .into(poster)

        filmName.text = result.title
        filmRate.text = result.voteAverage.toString()
        overview.text = result.overview
    }

    private fun initListeners() = with(mBinding) {
        backBtn.setOnClickListener {
            navigator().goBack()
        }
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
