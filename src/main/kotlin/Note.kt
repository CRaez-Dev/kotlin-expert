import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

data class Note(val title: String, val description: String, val type: Type) {
    enum class Type(val id: Int, val description: String) {
        TEXT(id = 1, description = "text"),
        AUDIO(id = 0, description ="audio")
    }
}

suspend fun getNotes() = flow<List<Note>> {
    var notes:List<Note> = emptyList()
    (1..10).map {
        delay(500)
        notes = notes + Note(
            title = "Title $it",
            description = "Description $it",
            type = if (it % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT
        )
        emit(notes)
    }

}

