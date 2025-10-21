package com.library.management.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.library.management.data.IssuedBook
import com.library.management.viewmodel.IssuedBookViewModel
import com.library.management.viewmodel.IssuedBookViewModelFactory

@Composable
fun ViewIssuedBooksScreen(navController: NavController) {
    val context = LocalContext.current
    val factory = IssuedBookViewModelFactory(context)
    val viewModel: IssuedBookViewModel = viewModel(factory = factory)

    val issuedBooks by viewModel.issuedBooks.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📚 Issued Books", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        if (issuedBooks.isEmpty()) {
            Text("No issued books found.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(issuedBooks) { book ->
                    IssuedBookCard(
                        issuedBook = book,
                        onDelete = { viewModel.deleteIssuedBook(it) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Dashboard")
        }
    }
}

@Composable
fun IssuedBookCard(issuedBook: IssuedBook, onDelete: (IssuedBook) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("📘 Book: ${issuedBook.bookTitle}", style = MaterialTheme.typography.bodyLarge)
            Text("👤 Member: ${issuedBook.memberName}", style = MaterialTheme.typography.bodyMedium)
            Text("📅 Issue: ${issuedBook.issueDate}", style = MaterialTheme.typography.bodySmall)
            Text("📆 Return: ${issuedBook.returnDate}", style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = { onDelete(issuedBook) }) {
                Text("Delete")
            }
        }
    }
}
