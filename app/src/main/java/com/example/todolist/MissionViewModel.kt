package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MissionViewModel : ViewModel() {

    val onNewTodo = MutableLiveData<String>()

    val todoLiveData: LiveData<List<Mission>> = MediatorLiveData<List<Mission>>().apply {
        addSource(onNewTodo) { text ->
            val todo = Mission(text, false)
            this.value = this.value!! + listOf(todo)
        }
    }
}