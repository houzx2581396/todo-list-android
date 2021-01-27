package com.example.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface MissionItemDao : GenericDao<MissionItem> {

    @Query("SELECT * FROM ${MissionItem.TABLE_NAME} ORDER BY ${MissionItem.COLUMN_CREATEDAT} DESC")
    fun findAll(): LiveData<List<MissionItem>>
}