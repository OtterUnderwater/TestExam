package com.example.testexam.view.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.testexam.R

@Composable
fun AuthView (controller: NavHostController){
    val context = LocalContext.current
    val vm = viewModel{ AuthViewModel() }

    Column (modifier = Modifier.fillMaxSize()
        .verticalScroll(rememberScrollState()).padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Spacer(Modifier.height(80.dp))
        Spacer(Modifier.weight(1f))
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.icons),
            contentDescription = "Android"
        )
        Spacer(Modifier.height(80.dp))
        Row (Modifier.fillMaxWidth()){
            Text(text = "Логин", color = Color(0xFF7E7E9A),
                modifier = Modifier.padding(bottom = 4.dp),
                textAlign = TextAlign.Start)
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = vm.state.login,
            onValueChange = { vm.updateState( vm.state.copy(login = it)) },
            placeholder = {  Text(text = "Введите логин", color = Color(0xFF17418C)) },
            shape = RoundedCornerShape(15.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor= Color(0xFF17418C),
                unfocusedBorderColor = Color(0xFF4CBBBF)
            )
        )
        Spacer(Modifier.height(15.dp))
        Row (Modifier.fillMaxWidth()){
            Text(text = "Пароль", color = Color(0xFF7E7E9A),
                modifier = Modifier.padding(bottom = 4.dp),
                textAlign = TextAlign.Start)
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = vm.state.password,
            onValueChange =  { vm.updateState( vm.state.copy(password = it)) },
            placeholder = {  Text(text = "Введите пароль", color = Color(0xFF17418C)) },
            shape = RoundedCornerShape(15.dp),
            visualTransformation = PasswordVisualTransformation('*'),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor= Color(0xFF17418C),
                unfocusedBorderColor = Color(0xFF4CBBBF)
            )
        )
        Spacer(Modifier.height(75.dp))
        Button(
            enabled = if (vm.state.login != "" && vm.state.password != "" ) true else false,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF17418C),
                disabledContainerColor = Color(0xFF8BA0C6),
                contentColor = Color.White
            ),
            onClick = { vm.auth(controller) }
        ){
            Text("Далее", fontSize = 25.sp, color = Color.White,  modifier = Modifier.padding( vertical = 4.dp))
        }
        Spacer(Modifier.weight(1f))
        Spacer(Modifier.height(100.dp))
    }

}