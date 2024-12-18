package com.example.testexam.view.screens.meme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.testexam.domain.Constans.currentMeme
import com.example.testexam.domain.Constans.currentUser
import com.example.testexam.domain.Constans.supabase
import com.example.testexam.models.database.FavoriteMemes
import com.example.testexam.models.database.Memes
import com.example.testexam.view.navigation.RoutesNav
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class MemeViewModel : ViewModel() {
    var listFavorit: List<FavoriteMemes> = listOf()

    fun launch(){
        viewModelScope.launch {
            listFavorit = supabase.from("FavoriteMemes").select() {
                filter {
                    eq("id_user", currentUser)
                }
            }.decodeList<FavoriteMemes>()
        }
    }

    fun addFavorit(meme: Memes, controller: NavHostController){
        viewModelScope.launch {
            supabase.from("FavoriteMemes").insert(
                FavoriteMemes(idUser = currentUser, idMeme = meme.id)
            )
            controller.navigate(RoutesNav.HOME){
                popUpTo(RoutesNav.MEME){
                    inclusive = true
                }
            }
        }
    }

    fun deleteFavorit(favoriteMeme: FavoriteMemes, controller: NavHostController){
        viewModelScope.launch {
            supabase.from("FavoriteMemes").delete {
                filter {
                    eq("id", favoriteMeme.id)
                }
            }
            controller.navigate(RoutesNav.HOME){
                popUpTo(RoutesNav.MEME){
                    inclusive = true
                }
            }
        }
    }

}