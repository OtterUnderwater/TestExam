package com.example.testexam.view.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.testexam.R
import com.example.testexam.models.database.Categories
import com.example.testexam.models.database.Memes

@Composable
fun HomeView (controller: NavHostController){
    val vm = viewModel { HomeViewModel() }
    val state = vm.state

    LaunchedEffect (Unit) {  vm.launch() }
    var color: Color
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 30.dp, vertical = 40.dp)){
        if (state.loading.value){
            CircularProgressIndicator(modifier = Modifier
                .size(100.dp)
                .padding(100.dp))
        }
        else {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.search,
                onValueChange =  { vm.filter(state.copy(search = it)) },
                leadingIcon = { Icon(painter = painterResource(R.drawable.search),
                    contentDescription = "", modifier = Modifier.size(20.dp)) },
                placeholder = {  Text(text = "Поиск", color = Color(0xFF17418C)) },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor= Color(0xFF17418C),
                    unfocusedBorderColor = Color(0xFF4CBBBF)
                )
            )
            Text("Каталог фильмов:", fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary)
            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)){
                items(state.listCategories){
                    if(it.id == state.selectedCategory.id)
                        color = MaterialTheme.colorScheme.primary
                    else
                        color = Color(0xFF979797)
                    ButtonFilter(it, color){
                        vm.filter(state.copy(selectedCategory = it))
                    }
                }
            }
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)){
                items(state.filterListMemes){
                    ItemMeme(it) {
                        vm.openItem(it, controller)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemMeme (item: Memes, onClick: () -> Unit){
    Row (modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically){
        val imageState = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.photo).size(300, 300).build()
        ).state
        if (imageState is AsyncImagePainter.State.Error || imageState is AsyncImagePainter.State.Loading){
            CircularProgressIndicator()
        }
        else if(imageState is AsyncImagePainter.State.Success){
            Image( painter = imageState.painter, contentDescription = "")
        }
        Column (modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(item.title, fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center)
        }
    }
    Divider(modifier = Modifier.fillMaxWidth(),
        thickness = 2.dp,color =MaterialTheme.colorScheme.secondary)
}

@Composable
fun ButtonFilter (item: Categories, color: Color, onClick: () -> Unit){
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        border = BorderStroke(2.dp, color)
    ) {
        Text(item.title, fontSize = 25.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center)
    }
}