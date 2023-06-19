package com.balajiprabhu.notes.presentation.notes

import com.balajiprabhu.notes.domain.model.Note
import com.balajiprabhu.notes.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
