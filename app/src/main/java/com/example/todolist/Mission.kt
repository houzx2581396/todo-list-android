package com.example.todolist

//sealed class Todo(val viewType: Int) {
//
//    data class Title(
//        val text: String
//    ) : Todo(TYPE_TITLE)
//
//    data class Mission(
//        val name: String,
//        val checked: Boolean
//    ) : Todo(TYPE_MISSION)
//
//    companion object {
//        const val TYPE_TITLE = 0
//        const val TYPE_MISSION = 1
//    }
//}
data class Mission(
    val name: String,
    val checked: Boolean
)
