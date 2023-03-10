package com.cyberwalker.fashionstore.home

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.cyberwalker.fashionstore.R


sealed class DrawerScreens(val title: String, val route: String) {
//    object Home : DrawerScreens("Home", "home")
//    object Account : DrawerScreens("Account", "account")
    object Logout : DrawerScreens("Logout", "Logout")
    object Help : DrawerScreens( "Help", "Help")
}

private val screens = listOf(
    DrawerScreens.Help,
    DrawerScreens.Logout
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onDestinationClicked: (route: String) -> Unit) {
    val activity = LocalContext.current as Activity
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        Image(
            modifier = Modifier.size(56.dp),
            painter = painterResource(R.drawable.ic_man),
            contentDescription = "App icon"
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = viewModel.user?.email ?: "no email",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(24.dp))
        Divider()
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.title,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.clickable {
                    if (screen.title == DrawerScreens.Logout.title) {
                        //signout
                        viewModel.logOutUser()
                        activity.finish()
                    }
                    onDestinationClicked(screen.route)
                }
            )
        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
//    FashionStoreTheme {
    Drawer(){}
//    }
}