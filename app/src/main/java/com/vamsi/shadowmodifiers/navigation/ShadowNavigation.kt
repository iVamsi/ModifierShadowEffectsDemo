package com.vamsi.shadowmodifiers.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vamsi.shadowmodifiers.ui.screens.ModifierShadowShowcase

sealed class Screen(val route: String) {
    object Showcase : Screen("showcase")
}

@Composable
fun ShadowNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Showcase.route
    ) {
        composable(Screen.Showcase.route) {
            ModifierShadowShowcase(modifier)
        }
    }
}