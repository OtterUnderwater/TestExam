package com.example.testexam.view.bottomBar


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testexam.R
import com.example.testexam.view.navigation.RoutesNav

@Composable
fun BottomBar(navController: NavHostController) {
    var colorBlue = MaterialTheme.colorScheme.primary
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {saveState = true}
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Image(painter = painterResource(
                        if (currentRoute == navItem.route) {
                            navItem.image2
                        }
                        else{
                            navItem.image
                        }
                    ),
                        modifier = Modifier.size(20.dp),
                        contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title,
                        color = if (currentRoute == navItem.route) {
                            colorBlue
                        }
                        else{
                            Color(0xFFD9D9D9)
                        }
                    )
                }
            )
        }
    }
}

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Фильм",
            image = R.drawable.home,
            image2 = R.drawable.home2,
            route = RoutesNav.HOME
        ),
        BarItem(
            title = "Избранное",
            image = R.drawable.favorit1,
            image2 = R.drawable.favorit2,
            route = RoutesNav.FAVORITE
        )
    )
}

data class BarItem(
    val title: String,
    val image: Int,
    val image2: Int,
    val route: String
)
