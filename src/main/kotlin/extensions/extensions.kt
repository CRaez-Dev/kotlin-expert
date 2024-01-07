package extensions

import androidx.compose.runtime.MutableState

fun <T> MutableState<T>.update(cModifier: (T) -> T) {
    value = cModifier(value)
}
