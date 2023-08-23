//package com.example.scatter.activity
//
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material3.*
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TabBarWithMenu() {
//    val tabs = listOf("Tab 1", "Tab 2", "Menu")
//    val images = listOf("@drawable/menu_burger", "@drawable/menu_burger")
//    val texts = listOf("Text for Tab 1", "Text for Tab 2")
//
//    var selectedTabIndex by remember { mutableStateOf(0) }
//    var showMenu by remember { mutableStateOf(false) }
//    val menuItems = listOf("Menu Item 1", "Menu Item 2")
//
//    Scaffold(
//        topBar = {
//            TabRow(selectedTabIndex) {
//                tabs.forEachIndexed { index, title ->
//                    Tab(
//                        selected = selectedTabIndex == index,
//                        onClick = {
//                            if (title == "Menu") {
//                                showMenu = !showMenu
//                            } else {
//                                selectedTabIndex = index
//                                showMenu = false
//                            }
//                        }
//                    ) {
//                        Text(title)
//                    }
//                }
//            }
//        }
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            if (showMenu) {
//                menuItems.forEach { menuItem ->
//                    Text(
//                        text = menuItem,
//                        modifier = Modifier
//                            .clickable { /* 메뉴 아이템 선택 시 동작 처리 */ }
//                            .padding(8.dp)
//                    )
//                }
//            } else {
//                Image(
//                    painter = painterResource(id = images[selectedTabIndex]),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(200.dp)
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(text = texts[selectedTabIndex])
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ToolbarWithTitleAndNavigationIconPreview() {
//    TabBarWithMenu()
//}