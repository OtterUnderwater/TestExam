package com.example.testexam.view.screens.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.testexam.domain.Constans
import com.example.testexam.domain.Constans.currentUser
import com.example.testexam.domain.Constans.supabase
import com.example.testexam.models.database.Users
import com.example.testexam.models.screens.StateAuth
import com.example.testexam.view.navigation.RoutesNav
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _state = mutableStateOf(StateAuth())
    val state: StateAuth get() = _state.value

    fun updateState(newState: StateAuth) {
        _state.value = newState
    }

    fun auth(controller: NavHostController){
        viewModelScope.launch {
            try {
                supabase.auth.signInWith(Email) {
                    email = state.login
                    password = state.password
                }
                val user = supabase.auth.currentUserOrNull()
                if (user != null) {
                    currentUser = user.id
                    controller.navigate(RoutesNav.HOME){
                        popUpTo(RoutesNav.AUTH){
                            inclusive = true
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Error screen AuthView",e.message.toString())
            }
        }
    }
}
