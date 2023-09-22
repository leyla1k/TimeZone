package com.example.timezone.ui.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.timezone.databinding.ItemResultBinding
import java.util.Collections
import com.example.timezone.data.Result

class ResultRVAdapter() : ListAdapter<Result, ResultViewHolder>(ResultDiffCallback()),
    ResultItemTouchHelperAdapter {

    lateinit var projects: MutableList<Result>
    lateinit var itemResultBinding: ItemResultBinding

    fun submit(list:  List<Result>, rv: RecyclerView) {
        projects=list.toMutableList()
        submitList(list){
            rv.invalidateItemDecorations()
        }//иначе добавление нового элемента - проблема
    }

    override fun onItemDismiss(position: Int) {
        projects.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(projects, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(projects, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        itemResultBinding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ResultViewHolder(itemResultBinding)
    }


    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)

    }

}