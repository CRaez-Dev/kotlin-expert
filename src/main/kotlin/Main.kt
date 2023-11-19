import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

class AppState {
    val notes = mutableStateOf(getNotes())
}

@Composable
@Preview
fun App(appState: AppState) {

    MaterialTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(appState.notes.value) {
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
}

fun main() {
    val appState = AppState()
    application {
        Window(onCloseRequest = ::exitApplication, title = "My Application") {
            App(appState)
        }
    }
}
