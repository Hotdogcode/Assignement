package com.test.assignment.ui.main.viewholders

import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.test.assignment.data.model.TrendingRepo
import com.test.assignment.databinding.RowTrendingRepoBinding


class MainViewHolder(private val binder: RowTrendingRepoBinding,private val onItemSelected: (repo: TrendingRepo, position: Int) -> Unit): RecyclerView.ViewHolder(binder.root),
    LifecycleOwner {

    var lifecycle = LifecycleRegistry(this)

    fun bind(trendingRepo : TrendingRepo) {
        binder.tvTitle.text = trendingRepo.name.common
        binder.tvSubTitle.text = trendingRepo.name.official
        binder.ivIcon.text = trendingRepo.flag
        itemView.setOnClickListener {
            onItemSelected(trendingRepo,bindingAdapterPosition)
        }
        if(trendingRepo.isSelected){
            binder.mainView.background = ColorDrawable(0xFF03DAC5.toInt())
        }else{
            binder.mainView.background = ColorDrawable(0x00000)
        }
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }
}