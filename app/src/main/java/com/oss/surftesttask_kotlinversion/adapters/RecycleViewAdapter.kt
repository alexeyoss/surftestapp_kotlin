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

class RecycleViewAdapter() :
    RecyclerView.Adapter<RecycleViewAdapter.mViewHolder>() {

    private var results: MutableList<Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_layout, parent, true)
        return mViewHolder(v)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.bindData(results[position])

    }

    override fun getItemCount(): Int = results.size

    class mViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = RvItemLayoutBinding.bind(itemView) //TODO

        fun bindData(result: Result){
            Glide.with(itemView.context).load(Constants.IMG_URL + result.posterPath)
                .fitCenter()
                .into(binding.poster)
            binding.title.text = result.title
            binding.overview.text = result.overview
            binding.date.text = result.releaseDate
            binding.icHeart.setBackgroundResource(R.drawable.ic_heart)
        }
    }

    fun getData(): MutableList<Result> = results

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newResult: MutableList<Result>) {
        results.clear()
        results.addAll(newResult)
        notifyDataSetChanged()
    }
}