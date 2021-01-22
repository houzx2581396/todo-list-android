package com.example.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MissionViewModel : ViewModel() {

    var missions: MutableLiveData<List<Todo>> = MutableLiveData(mutableListOf<Todo>())
    private var count = 0

    fun addNewTodo(name: String) {
        val newList: List<Todo> = missions.value?.toMutableList()?.apply {
            add(Todo.Mission("mission$count", false))
        } ?: listOf()
        missions.postValue(newList)

        count++
    }
}