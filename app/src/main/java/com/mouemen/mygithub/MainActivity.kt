package com.mouemen.mygithub

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moemen.mycalorietracker.navigation.Route
import com.mouemen.mygithub.ui.theme.MyGitHubTheme
import com.mouemen.repos_details_presentation.ReposDetailsScreen
import com.mouemen.repos_list_domain.model.ReposDataUiState
import com.mouemen.repos_list_presentation.ReposListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            MyGitHubTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.SPLASH

                    ) {
                        composable(Route.SPLASH) {
                            SplasScreen {
                                navController.navigate(Route.REPOS_LIST_SCREEN) {
                                    popUpTo(Route.SPLASH) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                        composable(Route.REPOS_LIST_SCREEN) {
                            ReposListScreen {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = "data", it
                                )
                                navController.navigate(Route.REPOS_DETAILS_SCREEN)
                            }
                        }
                        composable(Route.REPOS_DETAILS_SCREEN) {
                            val data =
                                navController.previousBackStackEntry?.savedStateHandle?.get<ReposDataUiState>(
                                    "data"
                                )
                            data?.let {
                                ReposDetailsScreen(it)
                            }
                        }
                    }
                }
            }
        }
    }
}