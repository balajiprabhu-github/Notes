package com.balajiprabhu.notes.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.balajiprabhu.notes.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDataBase : RoomDatabase() {

    abstract val noteDao : NoteDao


}