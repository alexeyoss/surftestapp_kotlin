package com.oss.surftesttask_kotlinversion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.databinding.ItemMoviesListLayoutBinding
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.utils.AdapterOnClickListener
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class RecycleViewAdapter(val mClickListener: AdapterOnClickListener) :
    RecyclerView.Adapter<RecycleViewAdapter.MViewHolder>() {

    private var results: List<Results> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movies_list_layout, parent, false)
        return MViewHolder(v)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bindData(results[position])
    }

    override fun getItemCount(): Int = results.size

    inner class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mBinding = ItemMoviesListLayoutBinding.bind(itemView)

        init {
            if (!itemView.hasOnClickListeners()) itemView.setOnClickListener {
                mClickListener.onItemClicked(absoluteAdapterPosition)
            }
        }

        fun bindData(results: Results) = with(mBinding) {
            Glide.with(itemView.context)
                .load(IMG_URL + results.posterPath)
//                .placeholder(DEFAULT_PICTURE) // TODO amend size of the picture
                .fitCenter()
                .into(poster)
            title.text = results.title
            overview.text = results.overview
            date.text = transformDate(results.releaseDate)
            icHeart.setBackgroundResource(R.drawable.ic_heart_empty_small)
        }

        private fun transformDate(date: String): String {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(NETWORK_DATE_PATTERN)).format(
                DateTimeFormatter.ofPattern(
                    LOCAL_DATE_PATTERN,
                    Locale(LOCAL_LANG)
                )
            ) ?: date
        }
    }

    fun setData(newResults: List<Results>) {
        val diffCallBack = MoviesDiffCallback(results, newResults)
        val diffResult = DiffUtil.calculateDiff(diffCallBack, false)
        results = newResults
        diffResult.dispatchUpdatesTo(this)
    }

    companion object {
        const val DEFAULT_PICTURE = R.drawable.ic_default_poster
        const val IMG_URL = "https://image.tmdb.org/t/p/w500"
        const val NETWORK_DATE_PATTERN = "yyyy-MM-dd"
        const val LOCAL_DATE_PATTERN = "dd-MM-yyyy"
        const val LOCAL_LANG = "ru"
    }
}

class MoviesDiffCallback(
    private val oldList: List<Results>,
    private val newList: List<Results>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = oldList[oldItemPosition]
        val newMovie = newList[newItemPosition]
        return oldMovie.id == newMovie.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = oldList[oldItemPosition]
        val newMovie = newList[newItemPosition]
        return oldMovie.title == newMovie.title
                && oldMovie.overview == newMovie.overview
                && oldMovie.releaseDate == newMovie.releaseDate
                && oldMovie.posterPath == newMovie.posterPath
    }
}
