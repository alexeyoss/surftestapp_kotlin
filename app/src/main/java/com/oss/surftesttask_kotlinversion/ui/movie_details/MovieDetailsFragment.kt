package com.oss.surftesttask_kotlinversion.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class MovieDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentMovieDetailsBinding

    // TODO Dependency Injection for getting data from one viewModel
    // TODO Rebuild the layout file for using the backdrop image instead of posterPath

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        initView()
        return mBinding.root
    }

    private fun initView() {
        // Get ActualData from ViewModel
        // Search certain movie via position
        // try to use the data binding
        // maybe we can do it in the isolated thread ?
    }

    companion object {
        private const val MOVIE_POSITION = "MOVIE_POSITION"

        fun newInstance(moviePosition: Int): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            fragment.arguments = bundleOf(MOVIE_POSITION to moviePosition)
            return fragment
        }
    }
}
