package ui.screens.home

import data.Filter
import data.Note
import data.fakeNotes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

operator fun <T> StateFlow<T>.getValue(owner:Any?, property:KProperty<*>): T = this.value
operator fun <T> MutableStateFlow<T>.setValue(owner:Any?, property:KProperty<*>,newValue:T){
    this.value = newValue
}

object HomeState {

    /*
    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
     */

    var state: UiState by MutableStateFlow(UiState())
        private set

    suspend fun loadNotes(coroutineScope: CoroutineScope) {

        coroutineScope.launch{
            state =  UiState(loading = true)

            Note.fakeNotes.collect{
                state = UiState(notes = it)
            }
        }
    }

    fun onFilterClick(filter: Filter){
        _state.update { it.copy(filter = filter) }
    }

    data class UiState(
        val notes: List<Note>? = null,
        val loading: Boolean  = false,
        val filter:Filter = Filter.All
    ){
        val filteredNotes get() = when(filter){
            Filter.All -> notes
            is Filter.ByType -> notes?.filter { it.type == filter.type }
        }
    }
}
