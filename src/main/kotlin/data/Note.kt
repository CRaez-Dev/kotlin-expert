package data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

data class Note(val title: String, val description: String, val type: Type) {
    enum class Type(val id: Int, val description: String) {
        TEXT(id = 1, description = "text"),
        AUDIO(id = 0, description ="audio")
    }
    companion object
}

operator fun Note.plus(other: Note): Note = Note(title, "$description ${other.description}", type)
fun test(list: List<Note>, note2: Note) = list + note2

val Note.Companion.fakeNotes get() = flow<List<Note>> {
    var notes:List<Note> = emptyList()
    delay(3000)

    notes = (1..10).map {
        Note(
            title = "Title $it",
            description = "Description $it",
            type = if (it % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT
        )
    }
    emit(notes)
}

