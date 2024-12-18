package com.example.testexam.view.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.testexam.domain.Constans.currentMeme
import com.example.testexam.domain.Constans.supabase
import com.example.testexam.models.database.Categories
import com.example.testexam.models.database.Memes
import com.example.testexam.models.screens.StateHome
import com.example.testexam.view.navigation.RoutesNav
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private var _state by mutableStateOf(StateHome())
    val state: StateHome get() = _state

    fun launch(){
        viewModelScope.launch {
            state.loading.value = true
            state.listMemes = supabase.from("Memes").select().decodeList<Memes>().toMutableList()
            state.filterListMemes = state.listMemes
            state.listCategories = supabase.from("Categories").select().decodeList<Categories>().toMutableList()
            state.listCategories.add(0,Categories("0", "Все"))
            state.loading.value = false
        }
    }

    fun openItem(value: Memes, controller: NavHostController){
        currentMeme = value
        controller.navigate(RoutesNav.MEME)
    }

    fun filter(newState: StateHome){
        _state = newState.copy( filterListMemes = state.listMemes )
        _state = _state.copy(loading = mutableStateOf(true))
        if (state.search != "") {
            _state = state.copy(filterListMemes = state.filterListMemes.filter {
                it.title.contains(state.search, ignoreCase = true)
            })
        }
        if (state.selectedCategory.title != "Все"){
            _state = state.copy( filterListMemes = state.filterListMemes.filter {
                it.idCategory == state.selectedCategory.id
            })
        }
        _state = _state.copy(loading = mutableStateOf(false))
    }

}