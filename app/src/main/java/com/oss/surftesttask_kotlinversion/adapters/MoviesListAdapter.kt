package com.oss.surftesttask_kotlinversion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.databinding.ItemMoviesListLayoutBinding
import com.oss.surftesttask_kotlinversion.models.Result
import com.oss.surftesttask_kotlinversion.support.Constants
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class RecycleViewAdapter() :
    RecyclerView.Adapter<RecycleViewAdapter.mViewHolder>() {

    private var results: MutableList<Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movies_list_layout, parent, false)
        return mViewHolder(v)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.bindData(results[position])
    }

    override fun getItemCount(): Int = results.size

    class mViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMoviesListLayoutBinding.bind(itemView)

        fun bindData(result: Result) = with(binding) {
            Glide.with(itemView.context)
                .load(Constants.IMG_URL + result.posterPath)
                .placeholder(Constants.DEFAULT_PICTURE)
                .fitCenter()
                .into(poster)
            title.text = result.title
            overview.text = result.overview
            date.text = transformDate(result.releaseDate as String)
            icHeart.setBackgroundResource(R.drawable.ic_heart_empty_small)

            bindViewListener(title.text)
        }

        private fun bindViewListener(title: CharSequence) {
            itemView.setOnClickListener {
                Snackbar.make(
                    itemView,
                    title.toString(),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
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

    fun setData(newResult: MutableList<Result>) {
        val diffCallBack = MoviesDiffCallback(results, newResult)
        val diffResult = DiffUtil.calculateDiff(diffCallBack, false)
        results = newResult
        diffResult.dispatchUpdatesTo(this)
//        results.clear()
//        results.addAll(newResult)
//        notifyDataSetChanged()
    }
}

class MoviesDiffCallback(
    private val oldList: MutableList<Result>,
    private val newList: MutableList<Result>
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
