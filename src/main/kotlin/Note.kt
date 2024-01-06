import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/*enum class Type(val id:Int, val description: String) {
    TEXT(1,"text") ,
    AUDIO(0,"audio")
}*/
data class Note(val title: String, val description: String, val type: Type) {
    enum class Type(val id: Int, val description: String) {
        TEXT(1, "text"),
        AUDIO(0, "audio")
    }
}
/*
val list: MutableList<Note> = mutableListOf(
    Note("Title 1", "Description 1", Note.Type.TEXT),
    Note("Title 2", "Description 2", Note.Type.AUDIO),
    Note("Title 3", "Description 3", Note.Type.AUDIO),
    Note("Title 4", "Description 4", Note.Type.AUDIO),
    Note("Title 5", "Description 5", Note.Type.TEXT),
    Note("Title 6", "Description 6", Note.Type.TEXT),
    Note("Title 7", "Description 7", Note.Type.AUDIO),
    Note("Title 8", "Description 8", Note.Type.AUDIO),
    Note("Title 9", "Description 9", Note.Type.TEXT),
    Note("Title 10", "Description 10", Note.Type.TEXT)
)
*/

suspend fun getNotes() = withContext(Dispatchers.IO) {
        delay(3000)
        val list = (1..10).map {
            Note(
                title = "Title $it",
                description = "Description $it",
                type = if (it % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT
            )
        }
    return@withContext list
}