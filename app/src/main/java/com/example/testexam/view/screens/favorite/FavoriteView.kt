package com.example.testexam.view.screens.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.testexam.models.database.Memes

@Composable
fun FavoriteView (controller: NavHostController){
    val vm = viewModel { FavoriteViewModel() }

    LaunchedEffect (Unit){
        vm.launch()
    }

    LazyColumn {
        items(vm.listMemes){
            ItemFavoriteMeme(it) {
                vm.openItem(it, controller)
            }
        }
    }
}

@Composable
fun ItemFavoriteMeme (item: Memes, onClick: () -> Unit){
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
        thickness = 2.dp,color = MaterialTheme.colorScheme.secondary)
}
