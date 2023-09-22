package com.example.timezone.ui.rv

import androidx.recyclerview.widget.DiffUtil
import com.example.timezone.data.Result

class ResultDiffCallback : DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Result, newItem: Result) =
        oldItem == newItem
}