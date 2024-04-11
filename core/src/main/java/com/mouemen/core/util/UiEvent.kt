package com.moemen.core.util

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
}