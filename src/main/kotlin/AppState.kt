import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AppState {

    var state: UiState by mutableStateOf(UiState())

    fun loadNotes(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            state =  UiState(loading = true)
            state =  UiState(notes = getNotes() )
        }
    }

    data class UiState(
        val notes: List<Note>? = null,
        val loading: Boolean = false
    )
}
