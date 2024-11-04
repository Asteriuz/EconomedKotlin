package com.asteriuz.economed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asteriuz.economed.ui.ForgotPasswordScreen
import com.asteriuz.economed.ui.HomeScreen
import com.asteriuz.economed.ui.LoginScreen
import com.asteriuz.economed.ui.register.RegisterOneScreen
import com.asteriuz.economed.ui.register.RegisterTwoScreen
import com.asteriuz.economed.ui.tabs.AppContent
import com.asteriuz.economed.ui.theme.EconomedTheme
import com.asteriuz.economed.ui.theme.White
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(500)
            )
        }
    ) {
        composable("home") { HomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("registerOne") { RegisterOneScreen(navController) }
        composable("registerTwo") { RegisterTwoScreen(navController) }
        composable("forgotPassword") { ForgotPasswordScreen(navController) }
        composable("AppContent") { AppContent(navController) }
    }
}

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Firebase.auth
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            EconomedTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = White
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
