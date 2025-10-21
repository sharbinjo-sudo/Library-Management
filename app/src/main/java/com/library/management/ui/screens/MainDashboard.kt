package com.library.management.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainDashboard(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "📚 Library Management",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
        )
        Spacer(modifier = Modifier.height(32.dp))

        // --- Book Management ---
        DashboardButton(
            text = "➕ Add Book",
            onClick = { navController.navigate("add_book") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        DashboardButton(
            text = "📖 View Books",
            onClick = { navController.navigate("view_books") }
        )

        Spacer(modifier = Modifier.height(24.dp))
        Divider(thickness = 1.dp)
        Spacer(modifier = Modifier.height(24.dp))

        // --- Member Management ---
        DashboardButton(
            text = "👤 Add Member",
            onClick = { navController.navigate("add_member") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        DashboardButton(
            text = "📋 View Members",
            onClick = { navController.navigate("view_members") }
        )

        Spacer(modifier = Modifier.height(24.dp))
        Divider(thickness = 1.dp)
        Spacer(modifier = Modifier.height(24.dp))

        // --- Issued Books ---
        DashboardButton(
            text = "📘 Issue Book",
            onClick = { navController.navigate("issue_book") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        DashboardButton(
            text = "📗 View Issued Books",
            onClick = { navController.navigate("view_issued") }
        )
    }
}

@Composable
fun DashboardButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
