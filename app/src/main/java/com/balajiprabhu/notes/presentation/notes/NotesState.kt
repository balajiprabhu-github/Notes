package com.balajiprabhu.notes.presentation.notes

import com.balajiprabhu.notes.domain.model.Note
import com.balajiprabhu.notes.domain.util.NoteOrder
import com.balajiprabhu.notes.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
