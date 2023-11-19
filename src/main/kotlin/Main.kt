import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.concurrent.thread

class AppState {

    val state = mutableStateOf(UiState())

    fun loadNotes() {
        thread {

            state.value = UiState(loading = true)
            getNotes { it -> state.value = UiState(notes = it, loading = false) }
        }
    }

    data class UiState(
        val notes: List<Note> = emptyList(),
        val loading: Boolean = false
    )
}

@Composable
@Preview
fun App(appState: AppState) {

    LaunchedEffect(true) {
        appState.loadNotes()
    }
    val notes = appState.state.value

    MaterialTheme {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            if(notes.loading) CircularProgressIndicator()
            NotesList(notes.notes)
        }

    }
}

@Composable
private fun NotesList(notes: List<Note>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(notes) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(0.8f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {

                    Row {
                        Text(
                            style = MaterialTheme.typography.h6,
                            text = it.title,
                            modifier = Modifier.weight(1f)
                        )
                        if (it.type == Note.Type.AUDIO) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = null
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(it.description)
                }
            }
        }
    }
}

fun main() {
    val appState = AppState()
    application {
        Window(onCloseRequest = ::exitApplication, title = "My Application") {
            App(appState)
        }
    }
}
