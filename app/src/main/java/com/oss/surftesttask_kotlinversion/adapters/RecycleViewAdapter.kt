package com.oss.surftesttask_kotlinversion.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.databinding.RvItemLayoutBinding
import com.oss.surftesttask_kotlinversion.models.Result
import com.oss.surftesttask_kotlinversion.support.Constants
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class RecycleViewAdapter() :
    RecyclerView.Adapter<RecycleViewAdapter.mViewHolder>() {

    private var results: MutableList<Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_layout, parent, false)
        return mViewHolder(v)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.bindData(results[position])
    }

    override fun getItemCount(): Int = results.size

    class mViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = RvItemLayoutBinding.bind(itemView)

        fun bindData(result: Result) = with(binding) {
            Glide.with(itemView.context).load(Constants.IMG_URL + result.posterPath)
                .fitCenter()
                .into(poster)
            title.text = result.title
            overview.text = result.overview
            date.text = transformDate(result.releaseDate as String)
            icHeart.setBackgroundResource(R.drawable.ic_heart)
        }

        private fun transformDate(date: String): String {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(
                DateTimeFormatter.ofPattern(
                    "dd MMMM yyyy",
                    Locale("ru")
                )
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newResult: MutableList<Result>) {
        results.clear()
        results.addAll(newResult)
        notifyDataSetChanged()
    }


}