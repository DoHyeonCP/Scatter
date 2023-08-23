package com.example.scatter.activity

import com.example.scatter.R
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scatter.ui.theme.NavigationDrawerComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


sealed class MenuItems(
    val 롯데월드: String,
    val 방이동_먹자골목: String,
    val 에비뉴엘월드타워점: String,
    val 롯데월드몰: String,
    val 올림픽공원: String,
)
class ComoseActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MeterialTheme

            ) {
                MyScaffoldLayout()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // 이걸 안붙이는 방법 찾아야함
@Composable
fun MyTopAppBar(onNaviagionIconClick: () -> Unit){
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = {
                onNaviagionIconClick()
            }){
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "navigation"
                )
            }
        }
    )
}

@Composable
fun MyNavigationDrawer() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { },
        content = {
            Scaffold(
                content = {
                    scope.launch{
                        drawerState.open()
                    }
                }
            )
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldLayout() {
//    val scaffoldState = rememberScaffoldState() M3 존재하지 않음 https://developer.android.com/jetpack/compose/designsystems/material2-material3?hl=ko
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val contextForToast = LocalContext.current.applicationContext

//    scaffoldState = scaffoldState 이거 왜 안됨.
    Scaffold(
        topBar = {
            MyTopAppBar {
                coroutineScope.launch{
                    drawerState.open()
                }
            }
        },

    )
        {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it), // use "it" parameter
        ) {
            // we will add a button to display the snackbar here
        }
    }
}

@Preview
@Composable
fun ToolbarSamplePreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        ) {
        MyScaffoldLayout()
    }
}