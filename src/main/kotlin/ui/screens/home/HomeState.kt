package ui.screens.home

import data.Note
import data.getNotes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object HomeState {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    suspend fun loadNotes(coroutineScope: CoroutineScope) {

        coroutineScope.launch{
            _state.value =  UiState(loading = true)

            getNotes().collect{
                _state.value = UiState(notes = it)
            }
        }
    }

    data class UiState(
        val notes: List<Note>? = null,
        val loading: Boolean  = false,
    )
}