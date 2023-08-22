//package com.example.scatter.activity
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//
//// sealed class로 정의하면, 다이나믹 데이터를 추가할 수 있지만,
//// enum class로 들어오변, iterate을 하기 편함
//
//enum class DrawerMenus(
//    val 롯데월드: String,
//    val 방이동_먹자골목: String,
//    val 에비뉴엘월드타워점: String,
//    val 롯데월드몰: String,
//    val 올림픽공원: String,
//){
//
//}
//
//// 메뉴들을 클릭했을 때 보여줄 UI 정의
//@Composable
//fun item(area: String){
//    Column(modifier = Modifier.fillMaxSize()){
//        Text(text = "$area")
//    }
//}
//
//// AppBar Composable, 페이지안에 추가해도되지만, 공통으로 단순한 형태를 사용할 경우
//// AppBar의 메뉴아이콘을 클릭하면, drawer 열리게 하다록 위함.
//@Composable
//fun TopBar(
//    onMenuClick: () -> Unit
//){
//    TopAppBar(
//        title = {Text("My App")},
//        navigationIcon = {
//            IconButton(onClick = onMenuClick) {
//
//            }
//        }
//    )
//}
//
//val scaffoldState = rememberScaffoldState()
//val scope = remeberCoroutineScope()
//Scaffold(
//    scaffoldState = scaffoldState,
//    drawerContent = {
//        NavDrawerHead()
//        NavDrawerBody(onItemClick = {menu ->
//            scope.launch{
//                scaffoldState.drawer
//            }
//        })
//    }
//
//)