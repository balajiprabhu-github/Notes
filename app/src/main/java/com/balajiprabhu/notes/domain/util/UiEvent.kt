package com.balajiprabhu.notes.domain.util

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
    object SaveNote: UiEvent()
}
