package com.balajiprabhu.notes.domain.usecase

import com.balajiprabhu.notes.domain.model.Note
import com.balajiprabhu.notes.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}