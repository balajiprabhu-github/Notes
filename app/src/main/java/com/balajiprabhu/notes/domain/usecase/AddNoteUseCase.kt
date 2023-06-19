package com.balajiprabhu.notes.domain.usecase

import com.balajiprabhu.notes.domain.model.InvalidNoteException
import com.balajiprabhu.notes.domain.model.Note
import com.balajiprabhu.notes.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {

        if (note.title.isBlank()) {
            throw InvalidNoteException("Title of the Note can't be empty")
        }

        if(note.content.isBlank()) {
            throw InvalidNoteException("Content of the Note can't be empty")
        }

        repository.insertNote(note)
    }
}