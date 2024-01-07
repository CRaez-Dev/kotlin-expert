package ui.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import data.Filter
import data.Note


@Composable
fun TopBar(onFilterClick:(Filter)->Unit) {
    TopAppBar(
        title = { Text("My Notes") },
        actions = {
            FilterAction(onFilterClick)
        }
    )
}

@Composable
private fun FilterAction(onFilterClick:(Filter)->Unit) {
    var expanded by remember { mutableStateOf(false) }

    @Composable
    infix fun Filter.ToMenuItem(text:String){
        DropdownMenuItem(onClick = {
            onFilterClick(this)
            expanded = false
        }) {
            Text(text = text)
        }
    }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = "Filter"
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

            Filter.All ToMenuItem "All"
            Filter.ByType(Note.Type.TEXT) ToMenuItem "Text"
            Filter.ByType(Note.Type.AUDIO) ToMenuItem "Audio"
            /*
            listOf(
                Filter.All to "All",
                Filter.ByType(Note.Type.TEXT) to "Text",
                Filter.ByType(Note.Type.AUDIO) to "Audio"
            ).forEach { (first,second)->
                DropdownMenuItem(onClick = {
                    onFilterClick(first)
                    expanded = false
                }) {
                    Text(text = second)
                }
            }
             */
        }
    }
}
