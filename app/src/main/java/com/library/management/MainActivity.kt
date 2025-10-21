package com.library.management

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.library.management.ui.screens.*
import com.library.management.ui.theme.LibraryManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryManagementTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LibraryNavHost()
                }
            }
        }
    }
}

@Composable
fun LibraryNavHost() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {

        // 🚀 Splash Screen
        composable("splash") {
            SplashScreen(navController)
        }

        // 🏠 Dashboard
        composable("dashboard") {
            MainDashboard(navController)
        }

        // 📚 Books
        composable("add_book") { AddBookScreen(navController) }
        composable("view_books") { ViewBooksScreen(navController) }

        // 👤 Members
        composable("add_member") { AddMemberScreen(navController) }
        composable("view_members") { ViewMembersScreen(navController) }

        // 📘 Issued Books
        composable("issue_book") { IssueBookScreen(navController) }
        composable("view_issued") { ViewIssuedBooksScreen(navController) }
    }
}
