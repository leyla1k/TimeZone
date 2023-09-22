package com.example.timezone.ui.rv

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.timezone.databinding.ItemResultBinding
import com.example.timezone.data.Result
import java.text.DateFormat

class ResultViewHolder(val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Result) {
        with(binding) {
            if (item.success) {
                tvSuccess.text = "Успех!"
                tvSuccess.setTextColor(Color.GREEN)
            } else {
                tvSuccess.text = "Ошибка!"
                tvSuccess.setTextColor(Color.RED)
            }
            tvTime.text = "Время затрачено(сек.): "+item.time/60
            tvDate.text = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(
                item.date.time)

        }
    }
}