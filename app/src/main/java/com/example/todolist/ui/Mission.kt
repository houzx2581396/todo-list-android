package com.example.todolist.ui

import java.util.*

data class Mission(
    val id: Int,
    val name: String,
    val checked: Boolean,
    val createdAt: Date
)
