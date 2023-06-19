package com.balajiprabhu.notes.data.repository

import com.balajiprabhu.notes.data.source.NoteDao
import com.balajiprabhu.notes.domain.model.Note
import com.balajiprabhu.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl constructor(private val noteDao: NoteDao) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNotesById(id)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }
}