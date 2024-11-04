package com.asteriuz.economed.ui.tabs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.asteriuz.economed.R
import com.asteriuz.economed.ui.theme.EconomedTheme
import com.asteriuz.economed.ui.theme.Normal
import com.asteriuz.economed.ui.theme.Primary
import com.asteriuz.economed.ui.theme.White


@Composable
fun AppContent(navController: NavController) {
    var currentTab by remember { mutableIntStateOf(0) }

    MainScreen(currentTab = currentTab, navController) { newIndex ->
        currentTab = newIndex
    }
}

@Composable
fun MainScreen(currentTab: Int, navController: NavController, onTabChange: (Int) -> Unit) {

    var tabHistory by remember { mutableStateOf(listOf(0)) }

    BackHandler(enabled = currentTab != 0) {
        val previousTab = tabHistory.dropLast(1).lastOrNull() ?: 0
        onTabChange(previousTab)
        tabHistory = tabHistory.dropLast(1)
    }


    LaunchedEffect(key1 = currentTab) {
        if (currentTab != tabHistory.last()) {
            tabHistory = tabHistory + currentTab
        }
    }

    val tabItems = listOf(
        TabItem("Home", R.drawable.ic_home) { HomeTabScreen() },
        TabItem("Unidades", R.drawable.ic_search) { HomeTabScreen() },
        TabItem(
            "Avisos",
            R.drawable.ic_notification
        ) { NotificationTabScreen(onNavigateHome = { onTabChange(0) }) },
        TabItem(
            "Perfil",
            R.drawable.ic_profile
        ) { PerfilTabScreen(navController = navController) }
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            when (currentTab) {
                0 -> tabItems[0].screen()
                1 -> tabItems[1].screen()
                2 -> tabItems[2].screen()
                3 -> tabItems[3].screen()
            }
        }
        TabRow(
            selectedTabIndex = currentTab,
            indicator = { },
            divider = { },
            containerColor = White,
            modifier = Modifier.fillMaxWidth(),
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selectedContentColor = Primary,
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.title,
                            tint = if (currentTab == index) Primary else Normal
                        )
                    },
                    text = {
                        Text(
                            text = item.title,
                            color = if (currentTab == index) Primary else Normal,
                            style = MaterialTheme.typography.labelSmall,
                            maxLines = 1
                        )
                    },
                    selected = currentTab == index,
                    onClick = { onTabChange(index) },
                )
            }
        }
    }
}

data class TabItem(
    val title: String,
    val iconRes: Int,
    val screen: @Composable () -> Unit
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EconomedTheme {
        val navController = rememberNavController()
        MainScreen(currentTab = 0, navController = navController) {}
    }
}