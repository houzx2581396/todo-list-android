package com.example.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import kotlinx.android.synthetic.main.mission_item.view.*

class MissionAdapter : ListAdapter<Mission, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<Mission>() {
        override fun areItemsTheSame(oldItem: Mission, newItem: Mission): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Mission, newItem: Mission): Boolean {
            return oldItem == newItem
        }

    }
) {
    interface OnMissionChangeListener {
        fun onChange(mission: Mission)
        fun delete(mission: Mission)
    }

    var onMissionChangeListener: OnMissionChangeListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MissionViewHolder(
            parent,
            onMissionChangeListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mission = getItem(position)
        (holder as MissionViewHolder).bind(mission)
    }
}

class MissionViewHolder(
    parent: ViewGroup,
    private val onMissionChangeListener: MissionAdapter.OnMissionChangeListener?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.mission_item, parent, false)
) {
    fun bind(mission: Mission) {
        val missionItem: AppCompatCheckBox = itemView.mission_item
        missionItem.text = mission.name
        missionItem.isChecked = mission.checked
        missionItem.setOnClickListener {
            onMissionChangeListener?.onChange(
                Mission(
                    mission.id,
                    mission.name,
                    mission.checked,
                    mission.createdAt
                )
            )
        }

        val deleteBtn: AppCompatButton = itemView.delete_mission_btn
        deleteBtn.setOnClickListener {
            onMissionChangeListener?.delete(
                Mission(
                    mission.id,
                    mission.name,
                    mission.checked,
                    mission.createdAt
                )
            )
        }
    }
}