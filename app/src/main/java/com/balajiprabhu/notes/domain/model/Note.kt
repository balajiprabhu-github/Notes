package com.balajiprabhu.notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.balajiprabhu.notes.ui.theme.Blue
import com.balajiprabhu.notes.ui.theme.LightGreen
import com.balajiprabhu.notes.ui.theme.Orange
import com.balajiprabhu.notes.ui.theme.Pink
import com.balajiprabhu.notes.ui.theme.Purple
import com.balajiprabhu.notes.ui.theme.Yellow
import java.sql.Timestamp

@Entity(tableName = "note")
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val notesColors = listOf(Pink,LightGreen,Purple,Yellow,Blue,Orange)
    }
}

class InvalidNoteException(message : String) : Exception(message)