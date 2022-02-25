package com.test.assignment.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.assignment.data.model.TrendingRepo
import com.test.assignment.databinding.RowTrendingRepoBinding
import com.test.assignment.ui.main.viewholders.MainViewHolder
import java.util.ArrayList

class MainAdapter( private val onItemSelected: (repo: TrendingRepo, position: Int) -> Unit) : RecyclerView.Adapter<MainViewHolder>() {
    private var items: ArrayList<TrendingRepo> = arrayListOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<TrendingRepo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun updateSelection(index:Int,select:Boolean){
        if(index>-1) {
            this.items[index].isSelected = select
            notifyItemChanged(index)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainViewHolder(RowTrendingRepoBinding.inflate(inflater, parent, false),onItemSelected)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}