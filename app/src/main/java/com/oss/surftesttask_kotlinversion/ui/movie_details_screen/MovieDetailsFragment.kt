package com.oss.surftesttask_kotlinversion.ui.movie_details_screen

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
    private lateinit var result: Results

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        initListeners()

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            result = savedInstanceState.getSerializable(KEY_STATE) as Results
            initViews(result)
        }
    }

    private fun initListeners() = with(mBinding) {

        navigator().listenResult(Results::class.java, viewLifecycleOwner) { source ->
            this@MovieDetailsFragment.result = source
            initViews(source)
        }

        backBtn.setOnClickListener {
            navigator().goBack()
        }
    }


    private fun initViews(source: Results) = with(mBinding) {
        Glide.with(root)
            .load(source.backdropPath)
            .placeholder(Constants.DEFAULT_POSTER)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .into(poster)

        filmName.text = source.title
        filmRate.text = source.voteAverage.toString()
        overview.text = source.overview
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, result)
    }

    companion object {
        @JvmStatic
        private val KEY_STATE = "KEY_STATE"
    }
}
