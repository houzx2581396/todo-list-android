package com.example.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MissionViewModel : ViewModel() {
    var missions: MutableLiveData<List<Mission>> = MutableLiveData(mutableListOf<Mission>())

    private var count = 0

    fun addNewTodo(name: String) {
        val newList: List<Mission> = missions.value?.toMutableList()?.apply {
            add(Mission("mission$count", false))
        } ?: listOf()

        missions.postValue(newList)

        count++
    }
}