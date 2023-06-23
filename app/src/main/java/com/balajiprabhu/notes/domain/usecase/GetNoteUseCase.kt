package com.balajiprabhu.notes.domain.usecase

import com.balajiprabhu.notes.domain.model.Note
import com.balajiprabhu.notes.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}