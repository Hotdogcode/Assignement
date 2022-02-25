package com.test.assignment.ui.main.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.test.assignment.data.model.TrendingRepo
import com.test.assignment.databinding.RowTrendingRepoBinding
import com.test.assignment.ui.main.adapter.MainAdapter
import com.test.assignment.utils.loadImage


class MainViewHolder(private val binder: RowTrendingRepoBinding,private val onItemSelected: (repo: TrendingRepo, position: Int) -> Unit): RecyclerView.ViewHolder(binder.root) {
    fun bind(trendingRepo : TrendingRepo) {
        binder.tvTitle.text = trendingRepo.name.common
        binder.ivIcon.loadImage(
            resource = "https://flagcdn.com/w320/me.png",
            requestOptions = RequestOptions().circleCrop()
        )
        itemView.setOnClickListener {
            onItemSelected(trendingRepo,bindingAdapterPosition)
        }
        if(trendingRepo.isSelected){
            binder.tvTitle.text = "Selected"
        }
    }
}