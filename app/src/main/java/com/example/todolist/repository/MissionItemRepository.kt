package com.example.todolist.repository

import androidx.lifecycle.LiveData
import com.example.todolist.data.AppDatabase
import com.example.todolist.data.MissionItem

class MissionItemRepository(
    private val database: AppDatabase
) {
    suspend fun insertMissionItem(missionItem: MissionItem) {
        database.missionItemDao().insert(missionItem)
    }

    fun getMissionItem(): LiveData<List<MissionItem>> {
        return database.missionItemDao().findAll()
    }

    suspend fun updateMission(missionItem: MissionItem) {
        database.missionItemDao().update(missionItem)
    }

    suspend fun deleteMission(missionItem: MissionItem) {
        database.missionItemDao().delete(missionItem)
    }
}
