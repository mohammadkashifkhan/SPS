package com.mdkashif.spsol.shared.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo(name = "note") val note: String
) {

    fun doesMatchSearchQuery(query: String) : Boolean {
        return note.contains(query)
    }
}
