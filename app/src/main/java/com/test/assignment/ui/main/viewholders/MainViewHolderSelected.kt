package com.test.assignment.ui.main.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.test.assignment.data.model.TrendingRepo
import com.test.assignment.databinding.RowTrendingRepoSelectedBinding
import com.test.assignment.utils.loadImage


class MainViewHolderSelected(private val binder: RowTrendingRepoSelectedBinding, private val onItemSelected: (repo: TrendingRepo, position: Int) -> Unit): RecyclerView.ViewHolder(binder.root) {
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