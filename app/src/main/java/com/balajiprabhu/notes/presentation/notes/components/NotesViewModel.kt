package com.balajiprabhu.notes.presentation.notes.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balajiprabhu.notes.domain.model.Note
import com.balajiprabhu.notes.domain.usecase.NoteUseCases
import com.balajiprabhu.notes.domain.util.NoteOrder
import com.balajiprabhu.notes.domain.util.OrderType
import com.balajiprabhu.notes.presentation.notes.NotesEvent
import com.balajiprabhu.notes.presentation.notes.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    private var recentlyDeletedNote : Note? = null
    private var getNoteJob : Job? = null

    val state: State<NotesState> = _state

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when(event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                        state.value.noteOrder.orderType == event.noteOrder.orderType) {
                    return
                }

                getNotes(event.noteOrder)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCases.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNoteJob?.cancel()
        getNoteJob = notesUseCases.getNotesUseCase(noteOrder)
            .onEach {  notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
    }

}