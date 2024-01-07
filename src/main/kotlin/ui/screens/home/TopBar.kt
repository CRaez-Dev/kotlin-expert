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
    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = "Filter"
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = {
                onFilterClick(Filter.All)
                expanded = false
            }) {
                Text(text = "All")
            }
            DropdownMenuItem(onClick = {
                onFilterClick(Filter.ByType(Note.Type.TEXT))
                expanded = false
            }) {
                Text(text = "Text")
            }
            DropdownMenuItem(onClick = {
                onFilterClick(Filter.ByType(Note.Type.AUDIO))
                expanded = false
            }) {
                Text(text = "Audio")
            }
        }
    }
}
