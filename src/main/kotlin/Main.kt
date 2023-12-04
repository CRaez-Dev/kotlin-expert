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

@Composable
@Preview
fun App(appState: AppState): Unit = with(appState) {

    if (appState.state.value.notes == null) {
        LaunchedEffect(true) {
            loadNotes()
        }
    }

    MaterialTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (appState.state.value.loading) CircularProgressIndicator()
            appState.state.value.notes?.let { NotesList(it) }
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
