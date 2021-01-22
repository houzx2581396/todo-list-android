package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.mission_item.view.*

class MissionAdapter : ListAdapter<Todo, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.viewType == newItem.viewType
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MissionViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val mission = getItem(position)) {
            is Todo.Mission -> (holder as MissionViewHolder).bind(mission)
        }
    }

}

class MissionViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.mission_item, parent, false)
) {
    fun bind(mission: Todo.Mission) {
        val missionItem: AppCompatCheckBox = itemView.mission_item
        missionItem.text = mission.name
        missionItem.isChecked = mission.checked
    }

}