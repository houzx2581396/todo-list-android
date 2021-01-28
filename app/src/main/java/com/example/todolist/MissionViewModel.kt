package com.example.todolist

import androidx.lifecycle.*
import com.example.todolist.data.MissionItem
import com.example.todolist.repository.MissionItemRepository
import kotlinx.coroutines.launch
import java.util.*

class MissionViewModel(private val repository: MissionItemRepository) : ViewModel() {

    val missionLiveData: LiveData<List<Mission>> = MediatorLiveData<List<Mission>>().apply {
        val source = repository.getMissionItem().map {
            it.map { missionItem ->
                Mission(
                    missionItem.id,
                    missionItem.mission,
                    missionItem.isFinished,
                    missionItem.createdAt
                )
            }
        }
        addSource(source) {
            this.value = it
        }
    }

    fun createNewMission(mission: String) {
        val missionItem = MissionItem(
            mission = mission,
            isFinished = false,
            createdAt = Date()
        )
        viewModelScope.launch {
            repository.insertMissionItem(missionItem)
        }
    }

    fun isFinished(mission: Mission) {
        val missionItem = MissionItem(
            mission = mission.name,
            isFinished = true,
            createdAt = Date()
        ).apply { id = mission.id }

        when (mission.checked) {
            true -> missionItem.isFinished = false
            false -> missionItem.isFinished = true
        }

        viewModelScope.launch {
            repository.updateMission(missionItem)
        }
    }

    fun deleteMission(mission: Mission) {
        val missionItem = MissionItem(
            mission = mission.name,
            isFinished = false,
            createdAt = Date()
        ).apply { id = mission.id }

        viewModelScope.launch {
            repository.deleteMission(missionItem)
        }
    }
}