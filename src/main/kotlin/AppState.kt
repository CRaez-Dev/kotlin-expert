import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object AppState {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    suspend fun loadNotes() {
        _state.value =  UiState(loading = true)
        _state.value =  UiState(notes = getNotes())
    }

    data class UiState(
        val notes: List<Note>? = null,
        val loading: Boolean  = false,
    )
}
