package com.oss.surftesttask_kotlinversion.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oss.surftesttask_kotlinversion.R

class RecycleViewAdapter(var mContext: Context, private var results: MutableList<String>) :
    RecyclerView.Adapter<RecycleViewAdapter.mViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_layout, parent, true)
        return mViewHolder(v)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = results.size

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun getData(): List<String> = results

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newResult: MutableList<String>) {
        results.clear()
        results.addAll(newResult)
        notifyDataSetChanged()
    }
}