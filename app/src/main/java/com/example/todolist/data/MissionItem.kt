package com.example.todolist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = MissionItem.TABLE_NAME
)
data class MissionItem(
    @ColumnInfo(name = COLUMN_MISSION) var mission: String,
    @ColumnInfo(name = COLUMN_ISFINISHED) var isFinished: Boolean,
    @ColumnInfo(name = COLUMN_CREATEDAT) var createdAt: Date
) {
    companion object {
        const val TABLE_NAME = "missions"

        const val COLUMN_ID = "id"
        const val COLUMN_MISSION = "mission"
        const val COLUMN_ISFINISHED = "is_finished"
        const val COLUMN_CREATEDAT = "created_at"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) var id: Int = 0
}
