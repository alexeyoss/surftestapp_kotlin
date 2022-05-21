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
import com.oss.surftesttask_kotlinversion.utils.Constants
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
        private val binding = ItemMoviesListLayoutBinding.bind(itemView)

        init {
            if (!itemView.hasOnClickListeners()) itemView.setOnClickListener {
                mClickListener.onItemClicked(absoluteAdapterPosition)
            }
        }

        fun bindData(results: Results) = with(binding) {
            Glide.with(itemView.context)
                .load(Constants.IMG_URL + results.posterPath)
                .placeholder(Constants.DEFAULT_PICTURE)
                .fitCenter()
                .into(poster)
            title.text = results.title
            overview.text = results.overview
            date.text = transformDate(results.releaseDate)
            icHeart.setBackgroundResource(R.drawable.ic_heart_empty_small)
        }

        private fun transformDate(date: String): String {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(
                DateTimeFormatter.ofPattern(
                    "dd MMMM yyyy",
                    Locale("ru")
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
