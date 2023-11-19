fun main() {
    val list: MutableList<Note> = mutableListOf(
        Note("Title 1", "Description 1"),
        Note("Title 2", "Description 2"),
        Note("Title 3", "Description 3"),
        Note("Title 4", "Description 4"),
        Note("Title 5", "Description 5"),
        Note("Title 6", "Description 6"),
        Note("Title 7", "Description 7"),
        Note("Title 8", "Description 8"),
        Note("Title 9", "Description 9"),
        Note("Title 10", "Description 10")
    )


    val set = setOf(
        Note("Title 1", "Description 1"),
        Note("Title 1", "Description 1"),
        Note("Title 1", "Description 1"),
        Note("Title 4", "Description 4"),
        Note("Title 5", "Description 5"),
        Note("Title 6", "Description 6"),
        Note("Title 7", "Description 7")
    )

    val map = mapOf<Int, Note>(
        Pair(1, Note("Title 1", "Description 1")),
        Pair(2, Note("Title 1", "Description 1")),
        Pair(3, Note("Title 1", "Description 1")),
        Pair(4, Note("Title 4", "Description 4")),
        Pair(5, Note("Title 5", "Description 5")),
        Pair(6, Note("Title 6", "Description 6")),
        Pair(7, Note("Title 7", "Description 7"))
    )

    val map2 = mapOf(
        1 to Note("Title 1", "Description 1"),
        2 to Note("Title 1", "Description 1"),
        3 to Note("Title 1", "Description 1"),
        4 to Note("Title 4", "Description 4"),
        5 to Note("Title 5", "Description 5"),
        6 to Note("Title 6", "Description 6"),
        7 to Note("Title 7", "Description 7")
    )


    list.add(Note("Title 8", "Description 8"))

    println(list)
    println(set)
    println(map)
    println(map2)


    val result = list
        .filterNot { it.title.contains('4') }
        .sortedBy { it.title }
        .map { it.title }



    println(result)
}