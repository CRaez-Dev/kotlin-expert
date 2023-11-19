import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun buildMessage(msg: String): String = "Sup $msg !"

class stateView {
    var message = mutableStateOf("")

    fun isButtonEnable() =  message.value.isNotEmpty()
    /*
    val buttonEnabled: Boolean
        get() = message.value.isNotEmpty()
    * */
}

@Composable
@Preview
fun App(state: stateView) {/*
    -   Cuando necesitas un estado que la interfaz valla a utilizar para actualizarce
        usa el remember mutableStateOf para que el valor sobreviva a las recomposiciones de compose

    -   remember es como un memo en react
    var text by remember { mutableStateOf("Hello, Raez World!") }
    */

    // val text = remember { mutableStateOf("") }
    // val message = "Sup! ${text.value}"


    MaterialTheme {
        Column {
            TextField(value = state.message.value, onValueChange = { newText -> state.message.value = newText  })
            Text(text = buildMessage(state.message.value))
            Button(onClick = { state.message.value ="" }, enabled = state.isButtonEnable()) {
                Text("Clean")
            }
        }
    }
}


fun main() {
    val state = stateView()
    application {
        Window(onCloseRequest = ::exitApplication, title = "My Application") {
            App(state)
        }
    }
}
