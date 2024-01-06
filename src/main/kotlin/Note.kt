import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

data class Note(val title: String, val description: String, val type: Type) {
    enum class Type(val id: Int, val description: String) {
        TEXT(1, "text"),
        AUDIO(0, "audio")
    }
}

suspend fun getNotes() = flow<Note> {
    (1..10).map {
        delay(500)
        emit(
            Note(
                title = "Title $it",
                description = "Description $it",
                type = if (it % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT
            )
        )
    }
}

