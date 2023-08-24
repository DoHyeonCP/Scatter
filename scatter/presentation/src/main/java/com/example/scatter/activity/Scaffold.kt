package com.example.scatter.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
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
import androidx.compose.ui.tooling.preview.Preview
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
fun MyTopAppBar(onNaviagionIconClick: () -> Unit) {
    TopAppBar(
        backgroundColor = Color(0xFF10316B),
        contentColor = Color.White,
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 네비게이션 아이콘
            IconButton(onClick = onNaviagionIconClick) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "navigation",
                    tint = Color.White, // 안하면 배경색이 같이 바뀜
                    modifier = Modifier.size(60.dp) // 아이콘 크기를 36dp로 키우기
                )
            }

            // 가운데 로고
            Image(
                painter = painterResource(id = R.drawable.congestionlogo),
                contentDescription = "center_image",
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            // 아이콘과 동일한 크기의 빈 공간을 만들어 로고를 중앙에 유지합니다.
            Spacer(modifier = Modifier.size(60.dp))
        }
    }
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

