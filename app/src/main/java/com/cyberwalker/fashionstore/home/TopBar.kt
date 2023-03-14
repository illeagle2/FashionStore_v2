package com.cyberwalker.fashionstore.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.ui.theme.ltgray
import com.cyberwalker.fashionstore.ui.theme.medium_14
import com.cyberwalker.fashionstore.ui.theme.small_caption
import com.cyberwalker.fashionstore.utils.showMessage
import kotlinx.coroutines.launch


@Composable
fun TopBar( viewModel: HomeViewModel,
            drawerState: DrawerState,
            scaffoldState : ScaffoldState,

){
    Row(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {

        val loggedInUser = viewModel.user

        val scope = rememberCoroutineScope()
        val context = LocalContext.current
        Box(
            modifier = Modifier.size(width = 37.dp, height = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Spacer(
                modifier = Modifier
                    .size(width = 37.dp, height = 40.dp)
                    .background(color = ltgray, shape = RoundedCornerShape(12.dp))
            )
            Image(
                modifier = Modifier
                    .size(width = 32.dp, height = 32.dp),
                painter = painterResource(id = R.drawable.ic_man),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Column {
            Text(text = "Welcome", style = MaterialTheme.typography.small_caption)
            Text(
                text = "Hi ${loggedInUser?.email ?: "default"}",
                style = MaterialTheme.typography.medium_14
            )
        }
        Spacer(modifier = Modifier.weight(1F))
        Image(
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    showMessage(context, "Drawer cliqued")

                    scope.launch {
//                        drawerState.apply {
//                            drawerState.open()
                          scaffoldState.drawerState.open()
//                        }
                    }
                }
                .padding(12.dp),
            painter = painterResource(id = R.drawable.menu_bar),
            contentDescription = null
        )
    }
}