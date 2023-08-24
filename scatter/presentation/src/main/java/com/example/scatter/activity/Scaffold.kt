package com.example.scatter.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

import com.example.scatter.R

@Composable
fun MyScaffoldLayout(content: @Composable (PaddingValues) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val contextForToast = LocalContext.current.applicationContext

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            MyTopAppBar {
                coroutineScope.launch{
                    scaffoldState.drawerState.open()
                }
            }
        },
        drawerContent = {
            MyNavigationDrawer(
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState
            )
        },){
        paddingValues -> content(paddingValues)
    }
}

@Composable
fun MyTopAppBar(onNaviagionIconClick: () -> Unit){
    TopAppBar(
        title = {
                Image(
                    painter = painterResource(id = R.drawable.congestionlogo),
                    contentDescription = "center_image",
              )
        },
        navigationIcon = {
            IconButton(onClick = {
                onNaviagionIconClick()
            }){
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "navigation"
                )
            }
        },
        backgroundColor = Color.Black
    )
}

@Composable
fun MyNavigationDrawer(
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // text
        Text(text = "Your UI Here")

        // gap between text and button
        Spacer(modifier = Modifier.height(height = 32.dp))

        // button
        Button(onClick = {
            // close the drawer
            coroutineScope.launch {
                scaffoldState.drawerState.close()
            }
        }) {
            Text(text = "Close Drawer")
        }
    }
}


